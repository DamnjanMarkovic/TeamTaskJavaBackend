package TeamTask.models;

import javax.persistence.*;
import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "users")
//@NamedQuery(name = "Integer.getSpecificUser",
//        query = "SELECT u.id from User u where u.userFirstName = ?1")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private UUID id;
    @Column(name = "username")
    private String userName;
    private String password;
    @Column(name = "status")
    private boolean active;
    @Column(name = "userfirstname")
    private String userFirstName;
//    @Column(name = "enabled")
//    private boolean enabled;

//    public User() {
////        super();
////        this.enabled=false;
//    }
//    private Integer id_image;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_token", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_token"))
//    private Token token;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_teams", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_team"))
    private Teams teams;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_images", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_image"))
    private Images images;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_task", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "taskid"))
//    private List<Task> listtask;


//    public User(String userName, String password, boolean active, String userFirstName, Integer id_image) {
//        this.userName = userName;
//        this.password = password;
//        this.active = active;
//        this.userFirstName = userFirstName;
//        this.id_image = id_image;
//    }

    public User() {
        super();
        this.active=false;
    }
    public User(UUID id) {
        this.id = id;
    }

    public User(String userName, String password, boolean active, String userFirstName) {
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.userFirstName = userFirstName;
    }

    public User(String userName, String password, boolean active, String userFirstName, Images image, Teams teams, Set<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.userFirstName = userFirstName;
        this.images = image;
        this.teams = teams;
        this.roles = roles;
    }

//    public User() {
//    }

//    public User(UUID id, String userName, String password, boolean active, String userFirstName, Integer id_image) {
//        this.id = id;
//        this.userName = userName;
//        this.password = password;
//        this.active = active;
//        this.userFirstName = userFirstName;
//        this.id_image = id_image;
//
//
//    }


    public User(UUID id, String userName, String password, boolean active, String userFirstName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.userFirstName = userFirstName;
    }

    public User(UUID id, String userName, String password, boolean active, String userFirstName, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.userFirstName = userFirstName;
        this.roles = roles;
    }

    public User(User user) {

        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.userFirstName = user.getUserFirstName();
        this.images = user.getImages();
        this.roles = user.getRoles();
        this.teams = user.getTeams();
    }


    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
