package pms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(String id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
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
