package TeamTask.models.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class UsersInTeamResponse implements Serializable {

    private String userFirstName;
    private Integer id_image;
    private String useremail;

    public UsersInTeamResponse(String userFirstName, Integer id_image, String useremail) {
        this.userFirstName = userFirstName;
        this.id_image = id_image;
        this.useremail = useremail;
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

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}
