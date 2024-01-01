import paho.mqtt.client as mqtt
import json
import time
import random

# Callback function for successful connection
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Thermometer connected to MQTT Broker!")
        client.connected_flag = True
    else:
        print(f"Failed to connect, return code {rc}")

# Callback function for disconnection
def on_disconnect(client, userdata, rc):
    print(f"Disconnected from MQTT Broker with return code {rc}")

# Broker address and client setup
broker_address = "mosquitto"
client = mqtt.Client("Thermometer")
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
        for patient_id in ["patient1", "patient2"]:
            # Increment the message count
            message_count += 1

            # Determine the temperature range based on message count
            if message_count % 10 == 0:
                # Every 10th time, generate a temperature outside the normal range
                if random.choice([True, False]):
                    temperature = round(random.uniform(35.0, 35.9), 1)
                else:
                    temperature = round(random.uniform(37.6, 38.5), 1)
            else:
                # For the other times, generate a temperature in the normal range
                temperature = round(random.uniform(36.0, 37.5), 1)

            # Create the message and determine the topic
            message = json.dumps({"temperature": temperature})
            topic = f"{patient_id}/temperature"

            # Publish the message to the MQTT broker
            print(f"Publishing {message} to topic {topic}")
            client.publish(topic, message, qos=0, retain=False)

            # Short delay before sending the next message for another patient
            time.sleep(15)

# Stop the client loop before exiting
client.loop_stop()
