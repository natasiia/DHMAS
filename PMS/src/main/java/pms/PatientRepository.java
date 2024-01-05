package pms;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, String> {
    // Additional custom queries can be defined here
    List<Patient> findByPatientId(Integer patientId);

}

