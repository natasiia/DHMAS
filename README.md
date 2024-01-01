# Distributed Health Monitoring and Alert System (DHMAS)

## Overview
DHMAS is a distributed system that monitors patients' health in real-time and issues alerts based on collected data.

## Components
### 1. Patient Data Collection Service (PDCS)
This is the starting point of the application. It uses the Internet of Things protocol MQTT to generate 4 random values (```heart_rate, respiratory_rate, SpO2, temperature```) for two different patients. To initialize this, navigate to the MQTT folder and perform the following:
```
# cd DHMAS/MQTT
docker-compose up
```

Next, set up the receiver, PDCS java module.
```
# cd DHMAS/PDCS
docker build -t pdcs .
docker run -e MQTT_BROKER_URL=tcp://host.docker.internal:1883 --name pdcs pdcs
```
This action will begin collecting MQTT messages and sending them to the HAS module via the Kafka service.
