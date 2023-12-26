package ans.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonDeserialize(using = AlertMessageDeserializer.class)
@SequenceGenerator(name="alert_message_seq", sequenceName="ALERT_MESSAGE_SEQ", allocationSize=1)
public class AlertMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="alert_message_seq")
    private Long id;

    private String message; // Email content

    @Column(name = "patient_id")
    private String patientId;

    private int age;
    private String gender;
    private String diagnosis;
    private String medication;

    @Column(name = "insurance_type")
    private String insuranceType;

    @Column(name = "treatment_duration")
    private Integer treatmentDuration;

    private LocalDateTime date;

    @Column(name = "heart_rate")
    private float heartRate;

    @Column(name = "respiratory_rate")
    private Integer respiratoryRate;

    @Column(name = "SpO2")
    private Integer spO2;

    private float temperature;
    private String status;
    private String email;

    @Transient // It won't be persisted in the database
    private GeneralInfo generalInfo;

    @Transient
    private StatusLocalis statusLocalis;


    // Constructor to populate fields from nested objects
    public AlertMessage(String message, GeneralInfo generalInfo, StatusLocalis statusLocalis) {
        this.message = message;
        if (generalInfo != null) {
            this.patientId = generalInfo.getPatient_id();
            this.age = generalInfo.getAge();
            this.gender = generalInfo.getGender();
            this.diagnosis = generalInfo.getDiagnosis();
            this.medication = generalInfo.getMedication();
            this.insuranceType = generalInfo.getInsurance_type();
            this.treatmentDuration = generalInfo.getTreatment_duration();
            this.email = generalInfo.getEmail();
        }
        if (statusLocalis != null) {
            this.date = statusLocalis.getDate();
            this.heartRate = statusLocalis.getHeart_rate();
            this.respiratoryRate = statusLocalis.getRespiratory_rate();
            this.spO2 = statusLocalis.getSpO2();
            this.temperature = statusLocalis.getTemperature();
            this.status = statusLocalis.getStatus();
        }
    }

}
