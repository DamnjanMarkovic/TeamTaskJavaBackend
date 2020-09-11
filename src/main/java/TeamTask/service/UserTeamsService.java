package TeamTask.service;

import TeamTask.models.User;
import TeamTask.models.dto.UsersInTeamResponse;
import TeamTask.repository.UserRepository;
import TeamTask.repository.UserTeamsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserTeamsService {
    private final UserTeamsRepository userTeamRepository;
    private final UserRepository userRepository;

    public UserTeamsService(UserTeamsRepository userTeamRepository, UserRepository userRepository) {
        this.userTeamRepository = userTeamRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<UsersInTeamResponse> getUsersInTeam(UUID teamUUID) {
        List<UUID> usersInTeamIDs = userTeamRepository.getUsersIDsInTeam(teamUUID);
        List<User> allUsers = new ArrayList<>();
        for (UUID uuidUser: usersInTeamIDs) {
            User user = userRepository.getUserOnID(uuidUser);
            allUsers.add(user);
        }
        return returnUsersToUserTeam(allUsers);
    }

    public List<UsersInTeamResponse> returnUsersToUserTeam(List<User> allUsers){
        List<UsersInTeamResponse> listUserResponse = new ArrayList<>();
        for (User us : allUsers) {
            UsersInTeamResponse userResponse = new UsersInTeamResponse(us.getUserFirstName(), us.getImages().getId_image(), us.getUseremail());
            listUserResponse.add(userResponse);
        }
        return listUserResponse;
    }





}
