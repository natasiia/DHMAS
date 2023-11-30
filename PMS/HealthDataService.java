package com.example.pms;

public class HealthDataService {
    // Autowire repositories

    public HealthData saveHealthDataForPatient(Long patientId, HealthData data) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (patient != null) {
            data.setPatient(patient);
            return healthDataRepository.save(data);
        }
        return null;
    }

    // Other methods
}

