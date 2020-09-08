package TeamTask.models.dto;

import java.io.Serializable;

public class UserRequest implements Serializable{

    private String username;
    private String password;
    private String userFirstName;
    private Integer id_image;
    private int id_team;
    private String role;

    public UserRequest(String username, String password, String userFirstName, Integer id_image, int id_team, String role) {
        this.username = username;
        this.password = password;
        this.userFirstName = userFirstName;
        this.id_image = id_image;
        this.id_team = id_team;
        this.role = role;
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

    public int getId_team() {
        return id_team;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
