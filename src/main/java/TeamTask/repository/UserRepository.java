package TeamTask.repository;

import TeamTask.models.dto.UserRequest;
import TeamTask.models.dto.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import TeamTask.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Modifying
    @Query(value = "DELETE FROM users WHERE status is false",
            nativeQuery = true)
    void removeIfFalse();

    @Modifying
    @Transactional
    @Query("UPDATE User us set us.userFirstName =(:userFirstName) where us.id =(:id)")
    void updateUserName(String userFirstName, UUID id);

    @Query(value = "SELECT id_role FROM role WHERE role_label = ?",
            nativeQuery = true)
    Integer getId_role (String role_label);


    @Query("SELECT ut.id_user from UserTeams ut where ut.id_team =(:id_team)")
    List<UUID> getUsersIDsInTeam(UUID id_team);

    @Query("SELECT user from User user where user.id =(:id)")
    User getUserOnID(UUID id);


    Optional<User> findByUserName(String username);

//    @Query("SELECT user from User user where user.useremail =(:useremail)")
//    User getUserOnEmail(String useremail);

    @Query("SELECT u.id from User u where u.userFirstName =(:userFirstName)")
    Integer getSpecificUser(String userFirstName);

    @Modifying
    @Query(value = "DELETE FROM user_restaurant WHERE id_user = ?",
            nativeQuery = true)
    void deleteUser_Roles(int id_user);

    @Modifying
    @Query(value = "DELETE FROM user_roles WHERE id_user = ?",
            nativeQuery = true)
    void deleteUser_Restaurant(int id_user);

    @Modifying
//    @Transactional
    @Query(value = "INSERT INTO user_restaurant (id_restaurant, id_user) " +
                    "VALUES (?, ?)", nativeQuery = true)
    void connectUserAndRestaurant(Integer id_restaurant, Integer id_user);

    @Query(value = "SELECT role.id_role FROM role WHERE role.role_label = ?",
            nativeQuery = true)
    Integer getIDRoleBasedOnRole(String role);


    @Query(value = "SELECT user.id_image FROM user",
            nativeQuery = true)
    List<Integer> getUsersIDs();

    @Modifying
//    @Transactional
    @Query(value = "INSERT INTO user_roles (id_user, id_role) VALUES (?, ?)",
            nativeQuery = true)
    void connectUserAndRoles(Integer id_user, Integer id_role);


}
