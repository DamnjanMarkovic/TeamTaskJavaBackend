package TeamTask.models.dto;



import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class UserResponse implements Serializable {

    private UUID id_user;
    private String userFirstName;
    private Integer id_image;
    private Set<String> role;
    private UUID id_team;
    private String useremail;


    public UserResponse(UUID id_user, String userFirstName, Integer id_image, Set<String> role, UUID id_team, String useremail) {
        this.id_user = id_user;
        this.userFirstName = userFirstName;
        this.id_image = id_image;
        this.role = role;
        this.id_team = id_team;
        this.useremail = useremail;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
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

    public UUID getId_team() {
        return id_team;
    }

    public void setId_team(UUID id_team) {
        this.id_team = id_team;
    }
}
