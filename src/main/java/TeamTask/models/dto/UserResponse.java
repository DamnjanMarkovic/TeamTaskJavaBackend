package TeamTask.models.dto;



import java.io.Serializable;
import java.util.Set;

public class UserResponse implements Serializable {

    private int id_user;
    private String userFirstName;
    private Integer id_image;
    private Set<String> role;
    private int id_team;


    public UserResponse(int id_user, String userFirstName, Integer id_image, Set<String> role, int id_team) {
        this.id_user = id_user;
        this.userFirstName = userFirstName;
        this.id_image = id_image;
        this.role = role;
        this.id_team = id_team;

    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public int getId_team() {
        return id_team;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }
}
