package alert.covid.util;

import alert.covid.models.User;
import alert.covid.models.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import alert.covid.repositories.VerificationTokenRepository;



import java.util.UUID;

/**
 * Class UserListener
 */
@Component
public class UserListener implements ApplicationListener<OnCreateUserEvent > {

    /**
     * Handles the application event when a user is created
     * @param event
     */
    @Override
    public void onApplicationEvent (OnCreateUserEvent event){
        this.confirmCreateUser(event);
    }

    private String serverUrl = "http://localhost:8080";

    @Autowired
    private JavaMailSender mailSender ;

    @Autowired
    private VerificationTokenRepository verifTokenRepository;

    /**
     * Sends an email to the user so they can validate their account
     * @param event
     */
    private void confirmCreateUser(OnCreateUserEvent event) {

        User user = event.getUser();

        String token = UUID.randomUUID().toString();
        VerificationToken verifToken = new VerificationToken();

        verifToken.setToken (token) ;
        verifToken.setUsername(user.getUsername());
        verifToken.setExpiryDate(verifToken.calculateExpiryDate(VerificationToken.EXPIRATION));

        verifTokenRepository.saveAndFlush(verifToken);

        String recipientAddess = user.getEmail();
        String subject ="User Account Confirmation";
        String confirmationUrl=event.getAppUrl()+"userConfirm?token="+token;
        String message = "Please confirm:";
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddess);
        email.setSubject(subject);
        email.setText(message+"\r\n"+serverUrl+confirmationUrl);
        mailSender.send(email);

    }



}
