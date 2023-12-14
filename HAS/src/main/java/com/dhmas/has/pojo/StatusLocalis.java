package com.dhmas.has.pojo;

import java.util.Date;

public class StatusLocalis implements java.io.Serializable{
    private Integer patient_id;
    private Date date;
    private float heart_rate;
    private Integer respiratory_rate;
    private Integer SpO2;
    private float temperature;
    private String status;


    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public Integer getSpO2() {
        return SpO2;
    }

    public void setSpO2(Integer spO2) {
        SpO2 = spO2;
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

    public StatusLocalis(Integer patient_id, Date date, float heart_rate, Integer respiratory_rate, Integer spO2, float temperature, String status) {
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
                "patient_id=" + patient_id +
                ", date=" + date +
                ", heart_rate=" + heart_rate +
                ", respiratory_rate=" + respiratory_rate +
                ", SpO2=" + SpO2 +
                ", temperature=" + temperature +
                ", status='" + status + '\'' +
                '}';
    }
}
