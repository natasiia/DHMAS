-- SQL script to create the status_localis table
CREATE TABLE status_localis (
    patient_id VARCHAR(255) NOT NULL,
    date DATETIME NOT NULL,
    heart_rate FLOAT,
    respiratory_rate INT,
    SpO2 INT,
    temperature FLOAT,
    status VARCHAR(50) NOT NULL CHECK (status IN ('Normal', 'Abnormal')),
    FOREIGN KEY (patient_id) REFERENCES general_info(patient_id)
);