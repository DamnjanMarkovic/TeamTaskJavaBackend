package TeamTask.component;

import TeamTask.models.User;
import TeamTask.models.dto.UserRequest;
import TeamTask.models.dto.VerificationToken;

public interface IUserService {

    User registerNewUserAccount(UserRequest userRequest) throws Exception;

    User registerNewUserAccountForNewUser(UserRequest userRequest) throws Exception;

    User getUser(String verificationToken);
//
    User saveRegisteredUser(UserRequest userRequest);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);
}
