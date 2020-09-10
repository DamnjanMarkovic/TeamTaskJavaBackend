package TeamTask.models.dto;



import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class UserResponse implements Serializable {

    private UUID id_user;
    private String userFirstName;
    private String imageLocation;
    private Set<String> role;
    private UUID id_team;


    public UserResponse(UUID id_user, String userFirstName, String imageLocation, Set<String> role, UUID id_team) {
        this.id_user = id_user;
        this.userFirstName = userFirstName;
        this.imageLocation = imageLocation;
        this.role = role;
        this.id_team = id_team;

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

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
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
