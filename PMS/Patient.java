package com.example.pms;
import javax.persistence.*;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String medicalHistory;

    // Assuming DeviceData is another entity representing data from PDCS
    @OneToMany(mappedBy = "patient")
    private List<DeviceData> deviceData;


}
