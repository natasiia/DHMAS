//
//
//import ans.EmailService;
//import ans.KafkaConsumerService;
//import ans.pojo.AlertMessage;
//import ans.pojo.AlertMessageRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class AnsApplicationTest {
//
//    @Mock
//    private EmailService emailService;
//
//    @Mock
//    private AlertMessageRepository alertMessageRepository;
//
//    @InjectMocks
//    private KafkaConsumerService kafkaConsumerService;
//
//    @Test
//    public void receiveMessageThenSendEmail() {
//        // Test mocked messages
//        String testMessage = "{\"generalInfo\":{\"patient_id\":2,\"age\":53,\"gender\":\"Male\",\"diagnosis\":\"Influenza\",\"medication\":\"Statins\",\"insurance_type\":\"Medicare\",\"treatment_duration\":298,\"email\":\"katherine77@example.com\"},\"statusLocalis\":{\"id\":1404,\"patient_id\":2,\"date\":[2024,1,3,18,28,5,588232000],\"heart_rate\":124.0,\"respiratory_rate\":34,\"temperature\":35.1,\"status\":\"Abnormal\",\"SpO2\":90}}";
//        kafkaConsumerService.listen(testMessage);
//
//        // Test if the email sent successfully and the alert message is saved to the repository
//        verify(emailService, times(1)).sendSimpleEmail(anyString(), anyString(), anyString());
//        verify(alertMessageRepository, times(1)).save(any(AlertMessage.class));
//    }
//}
