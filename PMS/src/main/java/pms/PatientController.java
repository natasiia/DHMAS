package pms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/statusLocalis")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<Patient>> getPatientsByPatientId(@PathVariable Integer patientId) {
        List<Patient> patients = patientService.getPatientsByPatientId(patientId);
        if (patients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patients);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Patient> updatePatient(@PathVariable String id, @RequestBody Patient patientDetails) {
//        Patient updatedPatient = patientService.updatePatient(id, patientDetails);
//        if (updatedPatient == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updatedPatient);
//    }

//    @DeleteMapping("/{id}")
//    public void deletePatient(@PathVariable String id) {
//        patientService.deletePatient(id);
//    }
}

