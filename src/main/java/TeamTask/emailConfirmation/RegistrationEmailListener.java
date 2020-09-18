package TeamTask.emailConfirmation;

import TeamTask.component.IUserService;
import TeamTask.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {
    @Autowired
    private IUserService userService;
    @Autowired
    private MailSender mailSender;
    @Override
    public void onApplicationEvent(OnRegistrationSuccessEvent event) {
        this.confirmRegistration(event);
    }
    private void confirmRegistration(OnRegistrationSuccessEvent event) {

    }

//    User user = event.getUser();
//    String token = UUID.randomUUID().toString();
//    userService.createVerificationToken(user,token);
}
