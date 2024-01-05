//package pdcs;
//
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.junit.Assert;
//import org.junit.Test;
//
//public class MainTest {
//
//    @Test
//    public void testConnectionToBroker() throws MqttException {
//        String brokerUrl = "tcp://localhost:1883";
//        String clientId = "TestClient";
//        MqttClient client = null;
//
//        try {
//            // Create and configure the MQTT client
//            client = new MqttClient(brokerUrl, clientId);
//            MqttConnectOptions connOpts = new MqttConnectOptions();
//            connOpts.setCleanSession(true);
//
//            // Attempt to connect to the broker
//            client.connect(connOpts);
//
//            // Verify that the client is connected
//            Assert.assertTrue(client.isConnected());
//        } catch (MqttException e) {
//            Assert.fail("Failed to connect to MQTT broker: " + e.getMessage());
//        } finally {
//            // Disconnect and close the client
//            if (client != null && client.isConnected()) {
//                try {
//                    client.disconnect();
//                } catch (MqttException e) {
//                    e.printStackTrace();
//                }
//                client.close();
//            }
//        }
//    }
//}
