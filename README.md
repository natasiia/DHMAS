<div style="text-align:center;">
<h1>
  Team Pioneers
  </h1>
</div>

### Project Description

DHMAS is a distributed system that monitors patients' health in real-time and issues alerts based on collected data.

DHMAS system consists of five components:

1. **Patient Data Collection Service (PDCS)**

- Uses MQTT clients and Mosquitto broker.
- Receives data from simulated devices.
- Forwards the messages to the HAS module.
- The MQTT folder contains a data generator to simulate real-time working medical devices, one for each recorded property:
    - Holter: generates random heart_rate every 15 seconds for 10 patients.
    - Oximeter: generates random SpO2 every 15 seconds for 10 patients.
    - Respirometer: generates random respiratory_rate every 15 seconds for 10 patients.
    - Thermometer: generates random temperature every 15 seconds for 10 patients.

2. **Health Analysis Service (HAS)**
- Serves as a Consumer of one Kafka queue and receives data from PDCS.
- Ensures a smooth connection between PDCS and HAS.
- Evaluates the received data, adds attributes, and writes to the database using MyBatis.
- Combines information from two tables to generate an alert message.
- Acts as a Producer for another Kafka queue and sends the newly assembled alert message to the ANS service.

3. **Alerting and Notification Service (ANS)**
- Receives data from the HAS module using Kafka.
- Sends emails to those altered patients.
- Uses MailHog to monitor sent emails.
- Stores received data and sends message information to the database.
- Exposes an API for sending altering emails.

4. **Patient Management Service (PMS)**
- Handles CRUD Operations.
- Acts as an intermediary between the tables and the information.
- Utilizes Get and Post requests.
- Implements security features as authentication measures in Spring Boot.
- Creates login credentials.

5. **Real-time Patient Monitoring Dashboard (PMD)**
- Summarizes the total number of different categories.
- Displays all patients’ personal information as a table.
- Adds filters to show what users want to focus on.
- Includes a component to insert new patients into the database.
- Counts and visualizes patients’ information as charts.

---

### Docker Version & Folder Explanation

- Under the project folder (dhmas-newest), run `docker-compose up`
- You may have to wait for 5 minutes to allow all services being ready
- You will see 14 containers, each built from one folder (**with the same name**):
    - mosquitto
        - This container is acting as a broker with sensors and PDCS.
    - oximeter, thermometer, respirometer, holter.
        - Those 4 containers are used to simulate sensors to generate user data.
    - pdcs
        - This container is the *Patient Data Collection Service*.
        - Connects 4 sensors through mosquitto.
        - Sends data to HAS using Kafka. The topic is *collection-analysis*.
    - has
        - This container is the Health Analysis Service.
        - Receives data from PDCS using Kafka. The topic is *collection-analysis.*
        - Sends data to ANS using Kafka. The topic is *analysis-alert*.
        - Stores received data into database using MyBatis.
    - ans
        - This container is the *Alerting and Notification Service*.
        - Receives data from HAS using Kafka. The topic is *analysis-alert*.
        - Stores received data into database using JPA.
    - pms
        - This container is the *Patient Management Service*.
        - Performs CRUD on two tables.
        - Modifies the database using JPA.
    - frontend
        - This container is the deployment of the front end React app.
        - Displays the information of the *general_info* table.
        - Shows dynamic alerting messages based on the *status_localis* table.
        - Interacts with PMS and ANS using the Restful API.
    - database
        - This container is the shared PostgreSql database.
        - Initializes 3 tables by *init.sql* script.
    - mailhog
        - This container is the fake SMTP server.
        - Used to show the sent emails.
        - Used by the ANS service
    - kafka
        - This container is the Kafka service used as a message queue between PDCS, HAS, and ANS.
        - The docker-compose has `KAFKA_CREATE_TOPICS: 'collection-analysis:1:1,analysis-alert:1:1 `settings so it will have two topics ready after built.
- Open `localhost:3000` to check the frontend

---

### Kubernetes Version

- To start, run `minikube start`, which downloads the Minikube image into Docker.
- Under the project folder run `./deploy.sh`. If it says lacking permission, please run `chmod +x deploy.sh`.
- Wait for all pods to get ready. You can check the pods' status using `kubectl get pods`. The pods may show errors; just wait since Kubernetes will restart pods automatically.
- After all pods are running, run `minikube service react-app --url`. You will see a URL after running.
- Open the URL in the browser, and you will see the same website as in the Docker Version.

### Report links


### Video links
