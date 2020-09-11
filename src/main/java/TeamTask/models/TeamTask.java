package TeamTask.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "team_task")
public class TeamTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_team_task;
    private UUID id_team;
    private UUID taskid;

    public TeamTask(Integer id_team_task, UUID id_team, UUID taskid) {
        this.id_team_task = id_team_task;
        this.id_team = id_team;
        this.taskid = taskid;
    }

    public Integer getId_team_task() {
        return id_team_task;
    }

    public TeamTask(TeamTask teamTask) {
        this.id_team_task = teamTask.getId_team_task();
        this.id_team = teamTask.getId_team();
        this.taskid = teamTask.getTaskid();
    }

    public void setId_team_task(Integer id_team_task) {
        this.id_team_task = id_team_task;
    }

    public UUID getId_team() {
        return id_team;
    }

    public void setId_team(UUID id_team) {
        this.id_team = id_team;
    }

    public UUID getTaskid() {
        return taskid;
    }

    public void setTaskid(UUID taskid) {
        this.taskid = taskid;
    }
}
