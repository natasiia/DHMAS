package com.dhmas.HAS.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusLocalis implements java.io.Serializable{
    @Id
    private Integer id;
    private Integer patient_id;
    private LocalDateTime date;
    private float heart_rate;
    private Integer respiratory_rate;
    @JsonProperty("SpO2")
    private Integer SpO2;
    private float temperature;
    private String status;

    public StatusLocalis(Integer patient_id, LocalDateTime date, float heart_rate, Integer respiratory_rate, Integer spO2, float temperature, String status) {
        this.patient_id = patient_id;
        this.date = date;
        this.heart_rate = heart_rate;
        this.respiratory_rate = respiratory_rate;
        SpO2 = spO2;
        this.temperature = temperature;
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusLocalis{" +
                "patient_id=" + patient_id.toString() +
                ", date=" + date +
                ", heart_rate=" + heart_rate +
                ", respiratory_rate=" + respiratory_rate +
                ", SpO2=" + SpO2 +
                ", temperature=" + temperature +
                ", status='" + status + '\'' +
                '}';
    }
}