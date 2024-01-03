import paho.mqtt.client as mqtt
import json
import time
import random

# Callback function for successful connection
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Oximeter connected to MQTT Broker!")
        client.connected_flag = True
    else:
        print(f"Failed to connect, return code {rc}")

# Callback function for disconnection
def on_disconnect(client, userdata, rc):
    print(f"Disconnected from MQTT Broker with return code {rc}")

# Broker address and client setup
broker_address = "mosquitto"
client = mqtt.Client("Oximeter")
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

            # Determine the saturation range based on message count
            if message_count % 10 == 0:
                # Every 10th time, generate a saturation in the range 85-90
                saturation = random.randint(85, 90)
            else:
                # For the other times, generate a saturation in the range 91-100
                saturation = random.randint(91, 100)

            # Create the message and determine the topic
            message = json.dumps({"SpO2": saturation})
            topic = f"{patient_id}/SpO2"

            # Publish the message to the MQTT broker
            print(f"Publishing {message} to topic {topic}")
            client.publish(topic, message, qos=0, retain=False)

            # Short delay before sending the next message for another patient
            time.sleep(15)

# Stop the client loop before exiting
client.loop_stop()
