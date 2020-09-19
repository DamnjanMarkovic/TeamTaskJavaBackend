package TeamTask.emailConfirmation;

import TeamTask.models.User;
import TeamTask.models.dto.UserRequest;
import TeamTask.models.Token;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends UserDetailsService {
    public User registerUser(UserRequest userRequest);

    public User findByUsername(String username);

    public User loginUser(UserRequest userRequest);

    public User findByUsernameAndPassword(String username, String password);

    public void createVerificationToken(User user, String token);

    public Token getVerificationToken(String verificationToken);

    public void enableRegisteredUser(User user);
}
