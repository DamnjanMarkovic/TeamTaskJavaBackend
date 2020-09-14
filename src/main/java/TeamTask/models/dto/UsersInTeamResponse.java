package TeamTask.models.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class UsersInTeamResponse implements Serializable {

    private UUID id_user;
    private String userFirstName;
    private Integer id_image;

    public UsersInTeamResponse(UUID id_user, String userFirstName, Integer id_image) {
        this.id_user = id_user;
        this.userFirstName = userFirstName;
        this.id_image = id_image;

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

}
