package pms;

import javax.persistence.*;


@Entity
@Table(name = "general_info")
public class HealthData {
    @Id
    @Column(name = "patient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    private Integer age;
    private String gender;
    private String diagnosis;
    private String medication;

    @Column(name = "insurance_type")
    private String insuranceType;

    @Column(name = "treatment_duration")
    private Integer treatmentDuration;

    @Column(name = "email")
    private String email;

    // Getters and setters

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public Integer getTreatmentDuration() {
        return treatmentDuration;
    }

    public void setTreatmentDuration(Integer treatmentDuration) {
        this.treatmentDuration = treatmentDuration;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
