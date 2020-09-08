package TeamTask.models;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "users")
//@NamedQuery(name = "Integer.getSpecificUser",
//        query = "SELECT u.id from User u where u.userFirstName = ?1")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private int id;
    @Column(name = "username")
    private String userName;
    private String password;
    @Column(name = "status")
    private boolean active;
    @Column(name = "userFirstName")
    private String userFirstName;
    private Integer id_image;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_teams", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_team"))
    private Teams teams;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;


    public User(String userName, String password, boolean active, String userFirstName, Integer id_image) {
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.userFirstName = userFirstName;
        this.id_image = id_image;
    }

    public User(String userName, String password, boolean active, String userFirstName, Integer id_image, Teams teams, Set<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.userFirstName = userFirstName;
        this.id_image = id_image;
        this.teams = teams;
        this.roles = roles;
    }

    public User() {
    }

    public User(int id, String userName, String password, boolean active, String userFirstName, Integer id_image) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.userFirstName = userFirstName;
        this.id_image = id_image;


    }





    public User(User user) {


        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.userFirstName = user.getUserFirstName();
        this.id_image = user.getId_image();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Integer getId_image() {
        return id_image;
    }

    public void setId_image(Integer id_image) {
        this.id_image = id_image;
    }
}
