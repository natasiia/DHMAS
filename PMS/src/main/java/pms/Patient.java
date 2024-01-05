package pms;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "status_localis")
public class Patient {
    @Id
    private Long id;

    @Column(name = "patient_id")
    private Integer patientId;
//    @ManyToOne
//    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false)
//    private HealthData healthData;

//    // Getters and setters for other fields...
//

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public HealthData getHealthData() {
//        return healthData;
//    }
//
//    public void setHealthData(HealthData healthData) {
//        this.healthData = healthData;
//    }

    private Date date;

    @Column(name = "heart_rate")
    private Float heartRate;

    @Column(name = "respiratory_rate")
    private Integer respiratoryRate;

    @Column(name = "SpO2")
    private Integer spo2;

    private Float temperature;
    private String status;

    // Getters and setters

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Float heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(Integer respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public Integer getSpo2() {
        return spo2;
    }

    public void setSpo2(Integer spo2) {
        this.spo2 = spo2;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
