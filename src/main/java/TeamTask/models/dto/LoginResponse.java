package TeamTask.models.dto;


import java.io.Serializable;
import java.util.Set;

public class LoginResponse implements Serializable {

    private int id_user;
    private final String jwt;
    private String username;
    private String userFirstName;
    private Integer id_image;
    private Set<String> role;
    private int id_team;


    public LoginResponse(int id_user, String jwt, String username,
                         String userFirstName, Integer id_image, Set<String> role, int id_team) {
        this.id_user = id_user;
        this.jwt = jwt;
        this.username = username;
        this.userFirstName = userFirstName;
        this.id_image = id_image;
        this.role = role;
        this.id_team = id_team;

    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId_team() {
        return id_team;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
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

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }


}
