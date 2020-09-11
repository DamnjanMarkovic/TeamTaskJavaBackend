package TeamTask.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "task")


public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "taskid")
    private UUID taskid;
    private String tasktitle;
    private String taskscheduled;
    private String tasktext;
    private String tasksetat;
    private boolean taskcompleted;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "team_task", joinColumns = @JoinColumn(name = "taskid"), inverseJoinColumns = @JoinColumn(name = "id_team"))
    private Teams teams;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_task", joinColumns = @JoinColumn(name = "taskid"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private User user;

    public Task() {

    }

    public Task(UUID taskid, String tasktitle, String taskscheduled, String tasktext, String tasksetat, boolean taskcompleted, Teams teams, User user) {
        this.taskid = taskid;
        this.tasktitle = tasktitle;
        this.taskscheduled = taskscheduled;
        this.tasktext = tasktext;
        this.tasksetat = tasksetat;
        this.taskcompleted = taskcompleted;
        this.teams = teams;
        this.user = user;
    }

    public UUID getTaskid() {
        return taskid;
    }

    public void setTaskid(UUID taskid) {
        this.taskid = taskid;
    }

    public String getTasktitle() {
        return tasktitle;
    }

    public void setTasktitle(String tasktitle) {
        this.tasktitle = tasktitle;
    }

    public String getTaskscheduled() {
        return taskscheduled;
    }

    public void setTaskscheduled(String taskscheduled) {
        this.taskscheduled = taskscheduled;
    }

    public String getTasktext() {
        return tasktext;
    }

    public void setTasktext(String tasktext) {
        this.tasktext = tasktext;
    }

    public String getTasksetat() {
        return tasksetat;
    }

    public void setTasksetat(String tasksetat) {
        this.tasksetat = tasksetat;
    }

    public boolean isTaskcompleted() {
        return taskcompleted;
    }

    public void setTaskcompleted(boolean taskcompleted) {
        this.taskcompleted = taskcompleted;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}