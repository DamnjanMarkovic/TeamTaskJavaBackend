package TeamTask.controler;

import TeamTask.emailConfirmation.IUserService;
import TeamTask.models.User;
import TeamTask.models.Token;
import TeamTask.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.Locale;

public class RegistrationController {

    @Autowired
    private IUserService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/registrationConfirm")
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        Token verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badToken";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return "redirect:/badToken";
        }

        user.setActive(true);

        tokenService.removeToken(token);

        return "redirect:/index";
    }
}
