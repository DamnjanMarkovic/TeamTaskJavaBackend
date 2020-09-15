package TeamTask.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_images")
public class UserImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user_images;
    private UUID id_user;
    private Integer id_image;

    public UserImages(UUID id_user, Integer id_image) {
        this.id_user = id_user;
        this.id_image = id_image;
    }


    public Integer getId_user_images() {
        return id_user_images;
    }

    public void setId_user_images(Integer id_user_images) {
        this.id_user_images = id_user_images;
    }

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public Integer getId_image() {
        return id_image;
    }

    public void setId_image(Integer id_image) {
        this.id_image = id_image;
    }
}
