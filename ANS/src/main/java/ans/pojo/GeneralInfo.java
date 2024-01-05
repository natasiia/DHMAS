package ans.pojo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralInfo implements java.io.Serializable{
    @Id
    private Integer patient_id;
    private Integer age;
    private String gender;
    private String diagnosis;
    private String medication;
    private String insurance_type;
    private Integer treatment_duration;
    private String email;

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