package social.net.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailOfGod;
    @Value("${activation.address}")
    private String activationAddress;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(String emailOfUser,String theme,String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailOfGod);
        simpleMailMessage.setTo(emailOfUser);
        simpleMailMessage.setSubject(theme);
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }

    public String makeMessage(String activationCode) {
        return String.format("Hello!\n" +
                "Welcome to Social8. Please, go to " +
                activationAddress+"%s",activationCode);
    }
}
