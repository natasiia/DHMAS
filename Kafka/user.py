import argparse
from faker import Faker
import random
from datetime import datetime
import time
import json
from confluent_kafka import Producer

fake = Faker()

# Argument parsing
parser = argparse.ArgumentParser(description='Simulate patient data.')
parser.add_argument('patient_id', type=int, help='Patient ID')
args = parser.parse_args()

# Kafka configuration
kafka_config = {'bootstrap.servers': 'localhost:9092'}

def kafka_producer_callback(err, msg):
    if err is not None:
        print(f"Failed to deliver message: {err}")
    else:
        print(f"Message delivered to {msg.topic()} [{msg.partition()}]")

producer = Producer(kafka_config)


def generate_status_localis(patient_id):
    date = datetime.now().isoformat()
    # A normal resting heart rate for adults ranges from 60 to 100 beats per minute.
    heart_rate = random.uniform(35, 125)
    # Normal respiration rates for an adult person at rest range from 12 to 16 breaths per minute.
    respiratory_rate = random.randint(10, 20)
    # A normal level of oxygen is usually 95% or higher.
    SpO2 = random.randint(89, 100)
    # Normal body temperature ranges from 97.5°F to 98.9°F (36.4°C to 37.2°C).
    temperature = random.uniform(35, 38)
    status = ""

    status_localis_data = {
        "patient_id": patient_id,
        "date": date,
        "heart_rate": heart_rate,
        "respiratory_rate": respiratory_rate,
        "SpO2": SpO2,
        "temperature": temperature,
        "status": status
    }
    return status_localis_data

while True:
    try:
        data = generate_status_localis(args.patient_id)
        print("Sending data:", json.dumps(data))
        producer.produce("data-collection", json.dumps(data), callback=kafka_producer_callback)
        producer.poll(0)
    except Exception as e:
        print("An error occurred:", e)
    time.sleep(10)
