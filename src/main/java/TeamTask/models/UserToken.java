package TeamTask.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "user_token")
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID id_user;
    private UUID id_token;

    public UserToken(Integer id, UUID id_user, UUID id_token) {
        this.id = id;
        this.id_user = id_user;
        this.id_token = id_token;
    }

    public UserToken() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public UUID getId_token() {
        return id_token;
    }

    public void setId_token(UUID id_token) {
        this.id_token = id_token;
    }
}
