package pms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
//    private Patient convertToEntity(Patient patientDTO) {
//        Patient patient = new Patient();
//
//        patient.setPatientId(Patient.getPatientId());
//        patient.setDate(Patient.getDate());
//        patient.setHeartRate(Patient.getHeartRate());
//        patient.setRespiratoryRate(Patient.getRespiratoryRate());
//        patient.setSpo2(Patient.getSpo2());
//        patient.setTemperature(Patient.getTemperature());
//        patient.setStatus(Patient.getStatus());
//
//        // If you have other fields in PatientDTO, map them here as well
//
//        return patient;
//    }
    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable String id) {
        return patientService.getPatientById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String id, @RequestBody Patient patientDetails) {
        Patient updatedPatient = patientService.updatePatient(id, patientDetails);
        if (updatedPatient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
    }
}

