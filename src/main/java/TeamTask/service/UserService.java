package TeamTask.service;

import TeamTask.component.IUserService;
import TeamTask.component.VerificationTokenRepository;
import TeamTask.models.*;
import TeamTask.models.dto.*;
import TeamTask.repository.*;
import TeamTask.util.JwtUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService, IUserService {
//public class UserService {

    private final UserRepository userRepository;
    private final ImagesRepository imagesRepository;
    private final TeamRepository teamRepository;
    private final UserTeamsRepository userTeamsRepository;
    private final UserImagesRepository userImagesRepository;
    private final UserRolesRepository userRolesRepository;
    private final TaskRepository taskRepository;
    private final UserTaskRepository userTaskRepository;
    private final TaskService taskService;
    private final UserTaskService userTaskService;
    private final TeamTaskService teamTaskService;
    private final JwtUtil jwtTokenUtil;
    private final VerificationTokenRepository tokenRepository;

    public UserService(UserRepository userRepository, ImagesRepository imagesRepository, TeamRepository teamRepository, UserTeamsRepository userTeamsRepository, UserImagesRepository userImagesRepository, UserRolesRepository userRolesRepository, TaskRepository taskRepository, UserTaskRepository userTaskRepository, TaskService taskService, UserTaskService userTaskService, TeamTaskService teamTaskService, JwtUtil jwtTokenUtil, VerificationTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.imagesRepository = imagesRepository;
        this.teamRepository = teamRepository;
        this.userTeamsRepository = userTeamsRepository;
        this.userImagesRepository = userImagesRepository;
        this.userRolesRepository = userRolesRepository;
        this.taskRepository = taskRepository;
        this.userTaskRepository = userTaskRepository;
        this.taskService = taskService;
        this.userTaskService = userTaskService;
        this.teamTaskService = teamTaskService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenRepository = tokenRepository;
    }

    @Transactional
    public void updateUserName(String userID, String newName) {
        UUID id_user = UUID.fromString(userID);
        userRepository.updateUserName(newName, id_user);
    }

    @Transactional
    public void updateUserImage(String imagename) {
//        UUID id_user = UUID.fromString(userID);
//        userRepository.updateUserName(newName, id_user);
    }

    @Transactional
    public List<UsersInTeamResponse> getUsersInTeam(UUID teamUUID) {
        List<UUID> usersInTeamIDs = userRepository.getUsersIDsInTeam(teamUUID);
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
            UsersInTeamResponse userResponse = new UsersInTeamResponse(us.getId(), us.getUserFirstName(), us.getImages().getId_image());
            listUserResponse.add(userResponse);
        }
        return listUserResponse;
    }
    @Transactional
    public void deleteUser (UUID id_user) {
//        UUID idUUID = UUID.fromString(id_user);
//
        Optional<User> user1 = userRepository.findById(id_user);
        Images images = new Images(user1.get().getUserFirstName());
        List<UUID> taskUUIDS = new ArrayList<>();
        List<UUID> fullTaskList = new ArrayList<>();

        taskUUIDS = taskService.returnTasksOnUserID(id_user);
        for (UUID idtask: taskUUIDS                 ) {
            fullTaskList.add(idtask);
        }

        userTaskService.deleteUserTasksOnUserID(id_user);

        for (UUID taskid:taskUUIDS             ) {
            Task task = new Task(taskid);
            taskRepository.delete(task);
            teamTaskService.deleteTeamTaskOnTeamID(task.getTaskid());
        }
        imagesRepository.deleteById(user1.get().getImages().getId_image());
//            imagesRepository.removeImage(images.getImagename());

        User user = new User(id_user);
        userRepository.delete(user);
//        userRepository.deleteById(id_user);

    }
//    public void confirmdeleteUser (UUID id_user) {
//        User user = new User(id_user);
//        userRepository.delete(user);
//
//        String idString = String.valueOf(id_user);
//        UUID idUUID = UUID.fromString(idString);
//        System.out.println(idUUID);
//        userRepository.removeIfFalse(idUUID);
//    }



    @Transactional
    public void deleteUsersInTeam (UUID id_team) {
        List<UUID> userIDsLIst = userTeamsRepository.getUserIDsOnTeamID(id_team);
        for (UUID id_user: userIDsLIst             ) {
            deleteUser(id_user);
        }



//        List<UUID> taskUUIDS = new ArrayList<>();
//        List<UUID> fullTaskList = new ArrayList<>();
//        for (UUID id_user: userIDsLIst             ) {
//            taskUUIDS = taskService.returnTasksOnUserID(id_user);
//            for (UUID idtask: taskUUIDS                 ) {
//                fullTaskList.add(idtask);
//            }
//        }
//        for (UUID id_user: taskUUIDS             ) {
//            userTaskService.deleteUserTasksOnUserID(id_user);
//
//        }
//        for (UUID taskid:taskUUIDS             ) {
//            Task task = new Task(taskid);
//            taskRepository.delete(task);
//        }
//
//
//        teamTaskService.deleteTeamTaskOnTeamID(id_team);
//
//        for (UUID id_user: userIDsLIst             ) {
//            taskService.deleteTasksOnUserID(id_user);
//        }
//        for (UUID id_user: userIDsLIst             ) {
//            User user = new User(id_user);
//
//            userRepository.delete(user);
//        }
    }

    @Transactional
    public UserDetails loadUserByUsername(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        try {
            user.orElseThrow(() -> new Exception("Not found: " + userName));
        } catch (Exception e) {
            throw new EntityNotFoundException();
        }
        return user.map(MyLoginDetails::new).get();
    }
    @Transactional
    public List<Images> getUsersPhotos() {
        List<Integer> listUsersIDs = userRepository.getUsersIDs();
        return getAllUsersPhotos(listUsersIDs);
    }



    private List<Images> getAllUsersPhotos(List<Integer> listUsersIDs) {
        Images imageUser = new Images();
        List<Images> listImageUsers = new ArrayList<>();
        for (Integer inter: listUsersIDs             ) {
            imageUser = imagesRepository.getSpecificPhotos(inter);
            if (imageUser!=null) {
                if (!listImageUsers.contains(imageUser)) {
                    listImageUsers.add(imageUser);
                }
            }
        }
        return listImageUsers;
    }


    @Transactional
    public List<UserResponse> getAll(){
        List<User> allUsers = userRepository.findAll();
        return returnUsersFormated(allUsers);
    }

//    @Override
//    public void saveRegisteredUser(User user) {
//
//    }
    @Override
//    @Transactional
    public User saveRegisteredUser(UserRequest userRequest) {
    String result = null;

        User user = new User(userRequest.getUsername(), userRequest.getPassword(),
                false, userRequest.getUserFirstName());
        Teams team = new Teams(userRequest.getName_team());
        System.out.println("tim je ");
        System.out.println(team.getId_team());
        team = teamRepository.save(team);
        user = userRepository.save(user);
        UserTeams userTeams = new UserTeams(user.getId(), team.getId_team());
        UserImages userImages = new UserImages(user.getId(), userRequest.getId_image());
        Integer id_role = userRepository.getId_role(userRequest.getRole());
        UserRoles userRole = new UserRoles(user.getId(), id_role);
        userRolesRepository.save(userRole);
        userTeamsRepository.save(userTeams);
        userImagesRepository.save(userImages);
        result = "User inserted in the DB";
        return user;
    }
    @Transactional
    public User addNewUserInTeam(UserRequest userRequest) throws SQLException {
        String result = null;
//da li ovde treba active true?
        User user = new User(userRequest.getUsername(), userRequest.getPassword(),
                false, userRequest.getUserFirstName());

        user = userRepository.save(user);
        UserTeams userTeams = new UserTeams(user.getId(), userRequest.getId_team());
        UserImages userImages = new UserImages(user.getId(), userRequest.getId_image());
        Integer id_role = userRepository.getId_role(userRequest.getRole());
        UserRoles userRole = new UserRoles(user.getId(), id_role);
        userRolesRepository.save(userRole);
        userTeamsRepository.save(userTeams);
        userImagesRepository.save(userImages);
        result = "User inserted in the DB";
        return user;
    }

    public List<UserResponse> returnUsersFormated(List<User> allUsers){
        List<UserResponse> listUserResponse = new ArrayList<>();
        for (User us : allUsers) {
            UserResponse userResponse = new UserResponse(us.getId(), us.getUserFirstName(),
                    us.getImages().getId_image(), us.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()),
                    us.getTeams().getId_team());
            listUserResponse.add(userResponse);
        }
        return listUserResponse;
    }


    @Transactional
    public String checkIfUsernameExists(String username) {
        Integer numberOFUsers = userRepository.checkUserexistance(username);
        if (numberOFUsers > 0) {
            return String.valueOf(userRepository.getUserIDBasedOnUserName(username));
        }
        return "User not existing in the DB";
    }

    @Transactional
    public LoginResponse getLoggedInUser(String id_userString) {
        UUID id_user = UUID.fromString(id_userString);
        Optional<User> us = userRepository.findById(id_user);
        LoginResponse loginResponse = new LoginResponse(us.get().getId(), "jwt", us.get().getUserName(), us.get().getUserFirstName(),
                us.get().getImages().getId_image(), us.get().getRoles().stream().map(Role::getRole).collect(Collectors.toSet()), us.get().getTeams().getId_team(), us.get().getTeams().getName_team());

        return loginResponse;

    }

    @Override
    public User registerNewUserAccount(UserRequest userRequest) throws Exception {
        if (emailExist(userRequest.getUsername())) {
            throw new Exception (
                    "There is an account with that email adress: "
                            + userRequest.getUsername());
        }
        return saveRegisteredUser(userRequest);
    }

    @Override
    public User registerNewUserAccountForNewUser(UserRequest userRequest) throws Exception {
        if (emailExist(userRequest.getUsername())) {
            throw new Exception (
                    "There is an account with that email adress: "
                            + userRequest.getUsername());
        }
        return addNewUserInTeam(userRequest);
    }

    @Override
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }
    private boolean emailExist(String email) {
        return userRepository.findByUserName(email) != null;
    }



    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(user.getId(), token);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
}

