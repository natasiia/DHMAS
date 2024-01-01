package com.dhmas.HAS.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class StatusLocalis implements java.io.Serializable{
    @Id
    private Integer id;
    private String patient_id;
    private LocalDateTime date;
    private float heart_rate;
    private Integer respiratory_rate;
    @JsonProperty("SpO2")
    private Integer spO2;
    private float temperature;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(float heart_rate) {
        this.heart_rate = heart_rate;
    }

    public Integer getRespiratory_rate() {
        return respiratory_rate;
    }

    public void setRespiratory_rate(Integer respiratory_rate) {
        this.respiratory_rate = respiratory_rate;
    }

    public void setSpO2(Integer spO2) {
        this.spO2 = spO2;
    }

    public Integer getSpO2() {
        return spO2;
    }


    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StatusLocalis() {
    }

    public StatusLocalis(String patient_id, LocalDateTime date, float heart_rate, Integer respiratory_rate, Integer spO2, float temperature, String status) {
        this.patient_id = patient_id;
        this.date = date;
        this.heart_rate = heart_rate;
        this.respiratory_rate = respiratory_rate;
        this.spO2 = spO2;
        this.temperature = temperature;
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusLocalis{" +
                "patient_id=" + patient_id +
                ", date=" + date +
                ", heart_rate=" + heart_rate +
                ", respiratory_rate=" + respiratory_rate +
                ", SpO2=" + spO2 +
                ", temperature=" + temperature +
                ", status='" + status + '\'' +
                '}';
    }
}
