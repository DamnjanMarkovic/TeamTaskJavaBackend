package TeamTask.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_teams")
public class UserTeams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user_teams;
    private UUID id_user;
    private UUID id_team;

    public UserTeams() {

    }

    public UserTeams(Integer id_user_teams, UUID id_user, UUID id_team) {
        this.id_user_teams = id_user_teams;
        this.id_user = id_user;
        this.id_team = id_team;
    }

    public UserTeams(UserTeams userTeams) {
        this.id_user_teams = userTeams.getId_user_teams();
        this.id_user = userTeams.getId_user();
        this.id_team = userTeams.getId_team();
    }

    public Integer getId_user_teams() {
        return id_user_teams;
    }

    public void setId_user_teams(Integer id_user_teams) {
        this.id_user_teams = id_user_teams;
    }

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public UUID getId_team() {
        return id_team;
    }

    public void setId_team(UUID id_team) {
        this.id_team = id_team;
    }
}


