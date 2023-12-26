package ans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final EmailService emailService;

    @Autowired
    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public String sendCustomEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendSimpleEmail(emailRequest.getEmailAddress(),
                    emailRequest.getSubject(),
                    emailRequest.getContent());
            return "Email sent successfully to " + emailRequest.getEmailAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send email to " + emailRequest.getEmailAddress();
        }
    }
}
