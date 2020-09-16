package TeamTask.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.type.PostgresUUIDType;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "teams")
public class Teams {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
//        @GeneratedValue
        @Column( columnDefinition = "uuid", updatable = false )
        private UUID id_team;
        private String name_team;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "user_teams", joinColumns = @JoinColumn(name = "id_team"), inverseJoinColumns = @JoinColumn(name = "id_user"))
//    private List<User> listUsers;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "team_task", joinColumns = @JoinColumn(name = "id_team"), inverseJoinColumns = @JoinColumn(name = "taskid"))
//    private List<Task> listTasks;

    public Teams() {
    }

    public Teams(UUID id_team) {
        this.id_team = id_team;
    }

    public Teams(UUID id_team, String name_team) {
        this.id_team = id_team;
        this.name_team = name_team;
    }

    public Teams(String name_team) {
        this.name_team = name_team;
    }


    public UUID getId_team() {
        return id_team;
    }

    public void setId_team(UUID id_team) {
        this.id_team = id_team;
    }

    public String getName_team() {
        return name_team;
    }

    public void setName_team(String name_team) {
        this.name_team = name_team;
    }


}
