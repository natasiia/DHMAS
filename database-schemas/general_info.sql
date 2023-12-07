-- SQL script to create the general_info table
CREATE TABLE general_info (
    patient_id VARCHAR(255) NOT NULL,
    age INT,
    gender VARCHAR(10) CHECK (gender IN ('Male', 'Female')),
    diagnosis VARCHAR(255) CHECK (diagnosis IN ('Heart Disease', 'Cancer', 'Diabetes', 'Influenza', 'Hypertension')),
    medication VARCHAR(255) CHECK (medication IN ('Aspirin', 'Antibiotics', 'Chemotherapy', 'Statins', 'Insulin')),
    insurance_type VARCHAR(50) CHECK (insurance_type IN ('Private', 'Uninsured', 'Medicaid', 'Medicare')),
    treatment_duration INT,
    PRIMARY KEY (patient_id)
);