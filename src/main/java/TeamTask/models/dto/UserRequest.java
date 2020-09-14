package TeamTask.models.dto;

import java.io.Serializable;
import java.util.UUID;

public class UserRequest implements Serializable {
    private UUID id_user;
    private String username;
    private String password;
    private String userFirstName;
    private Integer id_image;
    private String role;
    private UUID id_team;
    private String name_team;

    public UserRequest(UUID id_user, String username, String password, String userFirstName, Integer id_image, String role, UUID id_team, String name_team) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.userFirstName = userFirstName;
        this.id_image = id_image;
        this.role = role;
        this.id_team = id_team;
        this.name_team = name_team;
    }

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public Integer getId_image() {
        return id_image;
    }

    public void setId_image(Integer id_image) {
        this.id_image = id_image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getId_team() {
        return id_team;
    }

    public void setId_team(UUID id_team) {
        this.id_team = id_team;
    }

    public String getName_team() {
        return name_team;
    }

    public void setName_team(String name_team) {
        this.name_team = name_team;
    }
}
