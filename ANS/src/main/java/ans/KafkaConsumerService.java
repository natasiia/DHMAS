package ans;

import ans.pojo.AlertMessage;
import ans.pojo.AlertMessageRepository;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Service
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final AlertMessageRepository alertMessageRepository;
    private KafkaConsumer<String, String> kafkaConsumer;

    @Autowired
    public KafkaConsumerService(EmailService emailService, AlertMessageRepository alertMessageRepository) {
        this.emailService = emailService;
        this.alertMessageRepository = alertMessageRepository;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @PostConstruct
    public void init() {
        // Kafka Consumer Configuration
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","kafka:29092");
        properties.setProperty("group.id","ans");
        properties.setProperty("enable.auto.commit","true");
        properties.setProperty("auto.commit.interval.ms","1000");
        properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singletonList("analysis-alert"));
        consumeMessages();
    }

    public void consumeMessages() {
        new Thread(() -> {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    listen(record.value());
                }
            }
        }).start();
    }

    public void listen(String message) {
        try {
            System.out.println(message);
            AlertMessage alertMessage = objectMapper.readValue(message, AlertMessage.class);
            System.out.println(alertMessage.getDate());

            // Create email content
            String emailSubject = "Health Alert for Patient ID " + alertMessage.getPatientId();
            String emailContent = createEmailContent(alertMessage);
            String emailAddress = alertMessage.getEmail();

            // Send email
            emailService.sendSimpleEmail(emailAddress, emailSubject, emailContent);
            alertMessage.setMessage(emailContent);

            // Save to database
            alertMessageRepository.save(alertMessage);

            System.out.println("Received message: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String createEmailContent(AlertMessage alertMessage) {
        StringBuilder content = new StringBuilder();
        content.append("Health Alert for Patient ID: ").append(alertMessage.getPatientId()).append("\n\n");

        // Check and append specific alert reasons
        if (alertMessage.getHeartRate() > 120 || alertMessage.getHeartRate() < 90) {
            content.append("Abnormal Heart Rate Detected: ").append(alertMessage.getHeartRate()).append(" bpm\n");
        }
        if (alertMessage.getRespiratoryRate() < 17 || alertMessage.getRespiratoryRate() > 26) {
            content.append("Abnormal Respiratory Rate Detected: ").append(alertMessage.getRespiratoryRate()).append(" breaths/min\n");
        }
        if (alertMessage.getSpO2() < 90) {
            content.append("Low SpO2 Level Detected: ").append(alertMessage.getSpO2()).append("%\n");
        }
        if (alertMessage.getTemperature() < 36.0 || alertMessage.getTemperature() > 37.5) {
            content.append("Abnormal Temperature Detected: ").append(alertMessage.getTemperature()).append("°C\n");
        }
        content.append("\nOur healthcare team will review your health data and may contact you for further information or to discuss your treatment plan.");

        return content.toString();
    }

}
