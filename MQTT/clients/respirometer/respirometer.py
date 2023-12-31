import paho.mqtt.client as mqtt
import json
import time
import random

# Callback function for successful connection
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Respirometer connected to MQTT Broker!")
        client.connected_flag = True
    else:
        print(f"Failed to connect, return code {rc}")

# Callback function for disconnection
def on_disconnect(client, userdata, rc):
    print(f"Disconnected from MQTT Broker with return code {rc}")

# Broker address and client setup
broker_address = "mosquitto"
client = mqtt.Client("Respirometer")
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

            # Determine the respiratory rate range based on message count
            if message_count % 10 == 0:
                # Every 10th time, generate a rate in the range 10-16 or 26-35
                if random.choice([True, False]):
                    respiratory_rate = random.randint(10, 16)
                else:
                    respiratory_rate = random.randint(26, 35)
            else:
                # For the other times, generate a rate in the range 16-25
                respiratory_rate = random.randint(16, 25)

            # Create the message and determine the topic
            message = json.dumps({"respiratory_rate": respiratory_rate})
            topic = f"{patient_id}/respiratory_rate"

            # Publish the message to the MQTT broker
            print(f"Publishing {message} to topic {topic}")
            client.publish(topic, message, qos=0, retain=False)

            # Short delay before sending the next message for another patient
            time.sleep(2)

    # Wait 15 seconds before sending the next set of messages
    time.sleep(15)

# Stop the client loop before exiting
client.loop_stop()
