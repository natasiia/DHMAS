package pms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private HealthDataRepository healthDataRepository;
    @Autowired
    private PatientRepository patientRepository;
    public Patient savePatient(Patient patient) {
        String patientId = patient.getPatientId();
        if (patientId == null || patientId.isEmpty()) {
            throw new RuntimeException("Patient ID must not be null or empty");
        }

        // Check if HealthData exists for the given patient ID
        HealthData healthData = healthDataRepository.findById(patientId).orElse(null);
        if (healthData != null) {
            // If HealthData exists, set it to the patient and save
            patient.setHealthData(healthData);
            return patientRepository.save(patient);
        } else {
            // If HealthData does not exist, handle it (e.g., throw exception or save patient without HealthData)
            throw new RuntimeException("HealthData not found for patient ID: " + patientId);
            // Or, if you want to save the patient without HealthData:
            // return patientRepository.save(patient);
        }
    }


    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(String id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }

    public Patient updatePatient(String id, Patient patientDetails) {
        return patientRepository.findById(id)
                .map(patient -> {
                    // Update fields as needed
                    return patientRepository.save(patient);
                }).orElseGet(() -> {
                    patientDetails.setPatientId(id);
                    return patientRepository.save(patientDetails);
                });
    }
}
