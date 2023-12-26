package ans;

import ans.pojo.AlertMessage;
import ans.pojo.AlertMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final AlertMessageRepository alertMessageRepository;

    @Autowired
    public KafkaConsumerService(EmailService emailService, AlertMessageRepository alertMessageRepository) {
        this.emailService = emailService;
        this.alertMessageRepository = alertMessageRepository;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @KafkaListener(topics = "analysis-alert", groupId = "ans-group")
    public void listen(String message) {
        try {
//            System.out.println(message);
            AlertMessage alertMessage = objectMapper.readValue(message, AlertMessage.class);
            System.out.println(alertMessage.getDate());

            // Create email content
            String emailSubject = "Health Alert for Patient ID " + alertMessage.getPatientId();
            String emailContent = createEmailContent(alertMessage);
            String emailAddress = alertMessage.getEmail();

            // Send email
            emailService.sendSimpleEmail(emailAddress, emailSubject, emailContent);
            alertMessage.setMessage(emailContent);

            // Save to database
            alertMessageRepository.save(alertMessage);

            System.out.println("Received message: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String createEmailContent(AlertMessage alertMessage) {
        StringBuilder content = new StringBuilder();
        content.append("Health Alert for Patient ID: ").append(alertMessage.getPatientId()).append("\n\n");

        // Check and append specific alert reasons
        if (alertMessage.getHeartRate() > 120 || alertMessage.getHeartRate() < 40) {
            content.append("Abnormal Heart Rate Detected: ").append(alertMessage.getHeartRate()).append(" bpm\n");
        }
        if (alertMessage.getRespiratoryRate() < 35) {
            content.append("Abnormal Respiratory Rate Detected: ").append(alertMessage.getRespiratoryRate()).append(" breaths/min\n");
        }
        if (alertMessage.getSpO2() < 90) {
            content.append("Low SpO2 Level Detected: ").append(alertMessage.getSpO2()).append("%\n");
        }
        if (alertMessage.getTemperature() < 36.0 || alertMessage.getTemperature() > 37.5) {
            content.append("Abnormal Temperature Detected: ").append(alertMessage.getTemperature()).append("°C\n");
        }
        content.append("\nOur healthcare team will review your health data and may contact you for further information or to discuss your treatment plan.");

        return content.toString();
    }


}
