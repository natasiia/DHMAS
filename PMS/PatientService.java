package com.example.pms;

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

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
    @Autowired
    private PDCSService pdcsService;

    public Patient getPatientWithDeviceData(Long id) {
        Patient patient = getPatientById(id);
        if (patient != null) {
            List<DeviceData> deviceData = pdcsService.getDeviceDataForPatient(id);
            patient.setDeviceData(deviceData);
        }
        return patient;
    }

}

