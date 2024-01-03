package pdcs;

import com.google.gson.GsonBuilder;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.time.LocalDateTime;

public class Main {
    // Gson instance for JSON parsing
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    // Shared HealthData instances to store the latest values for each patient
    private static final HealthData healthDataPatient1 = new HealthData();
    private static final HealthData healthDataPatient2 = new HealthData();
    private static final HealthData healthDataPatient3 = new HealthData();
    private static final HealthData healthDataPatient4 = new HealthData();
    private static final HealthData healthDataPatient5 = new HealthData();
    private static final HealthData healthDataPatient6 = new HealthData();
    private static final HealthData healthDataPatient7 = new HealthData();
    private static final HealthData healthDataPatient8 = new HealthData();
    private static final HealthData healthDataPatient9 = new HealthData();
    private static final HealthData healthDataPatient10 = new HealthData();

    private static KafkaProducer<String, String> kafkaProducer;

    public static void main(String[] args) throws MqttException, InterruptedException {
        // MQTT broker URL and client ID
//        String brokerUrl = "tcp://host.docker.internal:1883";
        String brokerUrl = "tcp://localhost:1883";
        String clientId = "PDCSReceiver";
        // Persistence for the client, here using in-memory persistence
        MemoryPersistence persistence = new MemoryPersistence();

        // Create the MQTT client with the specified broker URL and client ID
        MqttClient client = new MqttClient(brokerUrl, clientId, persistence);
        // Connection options - here we ensure a clean session
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);

        // Kafka Producer setup
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("acks", "all");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducer = new KafkaProducer<>(kafkaProps);

        // Define the callback methods for the MQTT client
        client.setCallback(new MqttCallback() {
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                // Extract patient ID from the topic string
                int patientId = Integer.parseInt(topic.split("/")[0].replaceAll("\\D+", ""));

                // Parse the message payload into a JsonObject
                JsonObject jsonObject = gson.fromJson(new String(message.getPayload()), JsonObject.class);

                // Select the appropriate healthData object based on patient ID
//                HealthData healthData = (patientId == 1) ? healthDataPatient1 : healthDataPatient2;

                HealthData healthData = null;
                switch (patientId) {
                    case 1:
                        healthData = healthDataPatient1;
                        break;
                    case 2:
                        healthData = healthDataPatient2;
                        break;
                    case 3:
                        healthData = healthDataPatient3;
                        break;
                    case 4:
                        healthData = healthDataPatient4;
                        break;
                    case 5:
                        healthData = healthDataPatient5;
                        break;
                    case 6:
                        healthData = healthDataPatient6;
                        break;
                    case 7:
                        healthData = healthDataPatient7;
                        break;
                    case 8:
                        healthData = healthDataPatient8;
                        break;
                    case 9:
                        healthData = healthDataPatient9;
                        break;
                    case 10:
                        healthData = healthDataPatient10;
                        break;
                }



                // Set the patient ID in the health data
                healthData.setPatient_id(patientId);

                // Synchronize on the healthData object
                synchronized (healthData) {
                    // Update the healthData object with new values from the message
                    updateHealthData(jsonObject, healthData);
                    // Check if all fields are available
                    if (healthData.isComplete()) {
                        // Print the updated healthData
                        healthData.setDate(LocalDateTime.now());
                        System.out.println("Received data for patient " + patientId + ": " + healthData);
                        sendToKafka(healthData);
                    }
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // This method is called when message delivery is complete
            }

            @Override
            public void connectionLost(Throwable cause) {
                System.out.println(cause);
                // This method is called when the connection to the broker is lost
                System.out.println("Connection to MQTT broker lost!");
            }
        });

        // Connect to the MQTT broker
        System.out.println("Connecting to the MQTT broker...");
        client.connect(connOpts);
        System.out.println("Connected");

        // Subscribe to topics for each patient
        String[] topics = {"heart_rate", "SpO2", "respiratory_rate", "temperature"};
        String[] patients = {"patient1", "patient2", "patient3", "patient4","patient5", "patient6","patient7", "patient8",
                            "patient9", "patient10"};
        for (String patient : patients) {
            for (String topic : topics) {
                // Subscribe to each topic for both patients
                client.subscribe(patient + "/" + topic, 1);
            }
        }

        // Loop to keep the client running and processing messages
        while (true) {
        }
    }

    // Helper method to update health data from the JSON object
    private static void updateHealthData(JsonObject jsonObject, HealthData healthData) {
        // Iterate over JSON object entries and update corresponding health data fields
        jsonObject.entrySet().forEach(entry -> {
            switch (entry.getKey()) {
                case "heart_rate":
                    healthData.setHeart_rate(entry.getValue().getAsFloat());
                    break;
                case "SpO2":
                    healthData.setSpO2(entry.getValue().getAsInt());
                    break;
                case "respiratory_rate":
                    healthData.setRespiratory_rate(entry.getValue().getAsInt());
                    break;
                case "temperature":
                    healthData.setTemperature(entry.getValue().getAsFloat());
                    break;
            }
        });
    }

    private static void sendToKafka(HealthData data) {
        String jsonHealthData = gson.toJson(data);
        ProducerRecord<String, String> record = new ProducerRecord<>("pdcs_topic", null, jsonHealthData);
        kafkaProducer.send(record);
    }
}