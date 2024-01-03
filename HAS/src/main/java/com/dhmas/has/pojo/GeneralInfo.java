package com.dhmas.HAS.pojo;
public class GeneralInfo  implements java.io.Serializable{
    private Integer patient_id;
    private Integer age;
    private String gender;
    private String diagnosis;
    private String medication;
    private String insurance_type;
    private Integer treatment_duration;
    private String email;
    public Integer getPatient_id() {
        return patient_id;
    }
    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
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

    public String getInsurance_type() {
        return insurance_type;
    }

    public void setInsurance_type(String insurance_type) {
        this.insurance_type = insurance_type;
    }

    public Integer getTreatment_duration() {
        return treatment_duration;
    }

    public void setTreatment_duration(Integer treatment_duration) {
        this.treatment_duration = treatment_duration;
    }

    public void setEmail(String email) {this.email = email;}

    public String getEmail() {
        return email;
    }


    public GeneralInfo(Integer patient_id, Integer age,
                       String gender, String diagnosis,
                       String medication, String insurance_type,
                       Integer treatment_duration, String email) {
        this.patient_id = patient_id;
        this.age = age;
        this.gender = gender;
        this.diagnosis = diagnosis;
        this.medication = medication;
        this.insurance_type = insurance_type;
        this.treatment_duration = treatment_duration;
        this.email = email;
    }

    @Override
    public String toString() {
        return "GeneralInfo{" +
                "patient_id='" + patient_id.toString() + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", medication='" + medication + '\'' +
                ", insurance_type='" + insurance_type + '\'' +
                ", treatment_duration='" + treatment_duration +
                ", email= " + email + '\'' +
                '}';
    }
}