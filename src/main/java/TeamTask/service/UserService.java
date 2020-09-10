package TeamTask.service;

import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;
import TeamTask.models.Images;
import TeamTask.models.Role;
import TeamTask.models.dto.MyLoginDetails;
import TeamTask.models.User;
import TeamTask.models.dto.UserRequest;
import TeamTask.models.dto.UserResponse;
import TeamTask.repository.ImagesRepository;
import TeamTask.repository.UserRepository;
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

    public UserService(UserRepository userRepository, ImagesRepository imagesRepository) {
        this.userRepository = userRepository;
        this.imagesRepository = imagesRepository;
    }

    @Transactional
    public void deleteUser(Integer id)  {

        userRepository.deleteUser_Roles(id);
        userRepository.deleteUser_Restaurant(id);
        userRepository.deleteById(id);

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
                true, userRequest.getUserFirstName(), null,
                null, null);
        userRepository.save(user);
        Integer idNewUser = userRepository.getSpecificUser(user.getUserFirstName());
        userRepository.connectUserAndRestaurant(userRequest.getId_team(), idNewUser);
        Integer id_role = userRepository.getIDRoleBasedOnRole(userRequest.getRole());
        userRepository.connectUserAndRoles(idNewUser, id_role);
        result = "User inserted in the DB";
        return result;
    }


    public List<UserResponse> returnUsersFormated(List<User> allUsers){
        List<UserResponse> listUserResponse = new ArrayList<>();
        for (User us : allUsers) {
            UserResponse userResponse = new UserResponse(us.getId(), us.getUserFirstName(),
                    us.getImages().getImageLocation(), us.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()),
                    us.getTeams().getId_team());
            listUserResponse.add(userResponse);
        }
        return listUserResponse;
    }

    @Transactional
    public List<UserResponse> getUser(Integer id) {
        List<User> allUsers = userRepository.findAllById(Collections.singleton(id));
        List<UserResponse>listResponse = returnUsersFormated(allUsers);
        if (listResponse.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return listResponse;
    }
    @Transactional
    public Optional<User> getUserOnUsername(String userFirstName) {

        User userold = new User();
        userold.setUserFirstName(userFirstName);
        Example<User> user = Example.of(userold);
        return userRepository.findOne(user);
    }
}

