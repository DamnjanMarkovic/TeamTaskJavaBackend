package TeamTask.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_roles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="user_roles_sequence")
    private Integer id_user_roles;
    private UUID id_user;
    private Integer id_role;

    public UserRoles(UUID id_user, Integer id_role) {
        this.id_user = id_user;
        this.id_role = id_role;
    }

    public Integer getId_user_roles() {
        return id_user_roles;
    }

    public void setId_user_roles(Integer id_user_roles) {
        this.id_user_roles = id_user_roles;
    }

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public Integer getId_role() {
        return id_role;
    }

    public void setId_role(Integer id_role) {
        this.id_role = id_role;
    }
}
