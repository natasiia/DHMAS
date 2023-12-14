package pms.devices;
import pms.Patient;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class DeviceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fields like heartRate, respiratoryRate, etc.

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
