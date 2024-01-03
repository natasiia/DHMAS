import paho.mqtt.client as mqtt
import json
import time
import random

# Callback function for successful connection
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Holter connected to MQTT Broker!")
        client.connected_flag = True
    else:
        print(f"Failed to connect, return code {rc}")

# Callback function for disconnection
def on_disconnect(client, userdata, rc):
    print(f"Disconnected from MQTT Broker with return code {rc}")

# Broker address and client setup
broker_address = "mosquitto"
client = mqtt.Client("Holter")
client.on_connect = on_connect
client.on_disconnect = on_disconnect
client.connected_flag = False

# Connecting to the MQTT broker
client.connect(broker_address, 1883, 30)
client.loop_start()

# Counter to track the number of messages sent
message_count = 0

while True:
    if client.connected_flag:
        # Loop through the patient IDs
        for patient_id in ["patient1", "patient2","patient3", "patient4","patient5", "patient6","patient7", "patient8","patient9", "patient10"]:
            # Increment the message count
            message_count += 1

            # Determine the heart rate range based on message count
            if message_count % 20 == 0:
                # Every 10th time, generate a heart rate in the range 120-200
                heart_rate = float(random.randint(120, 200))
            else:
                # For the other times, generate a heart rate in the range 40-120
                heart_rate = float(random.randint(40.0, 120.0))

            # Create the message and determine the topic
            message = json.dumps({"heart_rate": heart_rate})
            topic = f"{patient_id}/heart_rate"

            # Publish the message to the MQTT broker
            print(f"Publishing {message} to topic {topic}")
            client.publish(topic, message, qos=0, retain=False)

            # Short delay before sending the next message for another patient
            time.sleep(15)

# Stop the client loop before exiting
client.loop_stop()
