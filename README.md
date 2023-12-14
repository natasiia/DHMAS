Saoirse PMS--> 

Firstly have to connect to the MySql Database
so i have the connection like this its in my application.properties you can change that if need be sorry 

### mysql -u root -p ### 

### CREATE DATABASE DHMAS; ###

### USE DHMAS; ###
### SOURCE /Users/saoirse/Desktop/DHMAS/database-schemas/general_info.sql; ###
### SOURCE DHMAS/database_schemas/status_localis.sql; ###
than i checked i had both obvs
DESCRIBE general_info;
DESCRIBE status_localis;

Than lets go over to the build
I have like mvn clean install
and than  mvn spring-boot:run

ok postman i did a get request using auth first saying basic and putting key in
http://localhost:8080/api/healthdata and than i used mysql to do this 

INSERT INTO general_info (patient_id, age, gender, diagnosis, medication, insurance_type, treatment_duration)
-> VALUES
->     ('patient1', 30, 'Male', 'Heart Disease', 'Aspirin', 'Private', 10),
->     ('patient2', 25, 'Female', 'Cancer', 'Chemotherapy', 'Uninsured', 15),
->     ('patient3', 40, 'Male', 'Diabetes', 'Insulin', 'Medicaid', 20);


ok now in postman again do the auth its baisc auth and than itll say generated password in the terminal 
but you can do post for healthdata with this in json
firstly in headers section its key is content-type and value is application/json than normal thing 
{
"patientId": "patient4",
"age": 35,
"gender": "Male",
"diagnosis": "Cancer",
"medication": "Aspirin",
"insuranceType": "Private",
"treatmentDuration": 25
}
MAKE SURE AS PATIENT_ID IS FOREIGN KEY THEY ARE LINKED ITS SAMNE ONE
{
"patient_id": "13456",
"date": "2023-12-14T12:00:00",
"heart_rate": 72.5,
"respiratory_rate": 18,
"SpO2": 98,
"temperature": 36.6,
"status": "Normal"
}


