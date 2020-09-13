package TeamTask.service;

import TeamTask.models.*;
import TeamTask.models.dto.UsersInTeamResponse;
import TeamTask.repository.*;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.type.PostgresUUIDType;
import org.springframework.transaction.annotation.Transactional;
import TeamTask.models.dto.MyLoginDetails;
import TeamTask.models.dto.UserRequest;
import TeamTask.models.dto.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
//public class UserService {

    private final UserRepository userRepository;
    private final ImagesRepository imagesRepository;
    private final TeamRepository teamRepository;
    private final UserTeamsRepository userTeamsRepository;
    private final UserImagesRepository userImagesRepository;
    private final UserRolesRepository userRolesRepository;

    public UserService(UserRepository userRepository, ImagesRepository imagesRepository, TeamRepository teamRepository, UserTeamsRepository userTeamsRepository, UserImagesRepository userImagesRepository, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.imagesRepository = imagesRepository;
        this.teamRepository = teamRepository;
        this.userTeamsRepository = userTeamsRepository;
        this.userImagesRepository = userImagesRepository;
        this.userRolesRepository = userRolesRepository;
    }

    @Transactional
    public void deleteUser(Integer id)  {

        userRepository.deleteUser_Roles(id);
        userRepository.deleteUser_Restaurant(id);
//        userRepository.deleteById(id);

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


    @Transactional
    public String save(UserRequest userRequest) throws SQLException {
    String result = null;

        User user = new User(userRequest.getUsername(), userRequest.getPassword(),
                true, userRequest.getUserFirstName());
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
        return result;
    }
    @Transactional
    public String addNewUserInTeam(UserRequest userRequest) throws SQLException {
        String result = null;

        User user = new User(userRequest.getUsername(), userRequest.getPassword(),
                true, userRequest.getUserFirstName());

        user = userRepository.save(user);
        UserTeams userTeams = new UserTeams(user.getId(), userRequest.getId_team());
        UserImages userImages = new UserImages(user.getId(), userRequest.getId_image());
        Integer id_role = userRepository.getId_role(userRequest.getRole());
        UserRoles userRole = new UserRoles(user.getId(), id_role);
        userRolesRepository.save(userRole);
        userTeamsRepository.save(userTeams);
        userImagesRepository.save(userImages);
        result = "User inserted in the DB";
        return result;
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
    public List<UserResponse> getUser(UUID id) {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(userRepository.getUserOnID(id));
        return returnUsersFormated(allUsers);


////        List<User> allUsers = userRepository.findAllById(Collections.singleton(id));
//        List<UserResponse>listResponse = returnUsersFormated(allUsers);
//        if (listResponse.isEmpty()) {
//            throw new EntityNotFoundException();
//        }
//        return listResponse;
//        return null;
    }
//    @Transactional
//    public List<UserResponse> getUserOnEmail(String useremail) {
//        List<User> allUsers = new ArrayList<>();
//        allUsers.add(userRepository.getUserOnEmail(useremail));
//       return returnUsersFormated(allUsers);
//
//    }
}

