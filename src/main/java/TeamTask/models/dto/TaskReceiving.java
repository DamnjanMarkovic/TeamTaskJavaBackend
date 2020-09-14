package TeamTask.models.dto;

import java.io.Serializable;
import java.util.UUID;

public class TaskReceiving implements Serializable  {

    private UUID taskid;
    private String tasktitle;
    private long taskscheduled;
    private String tasktext;
    private long tasksetat;
    private Boolean taskcompleted;
    private UUID id_user;
    private UUID id_team;


    public TaskReceiving(UUID taskid, String tasktitle, long taskscheduled, String tasktext, long tasksetat, boolean taskcompleted, UUID id_user, UUID id_team) {
        this.taskid = taskid;
        this.tasktitle = tasktitle;
        this.taskscheduled = taskscheduled;
        this.tasktext = tasktext;
        this.tasksetat = tasksetat;
        this.taskcompleted = taskcompleted;
        this.id_user = id_user;
        this.id_team = id_team;
    }

//    public Boolean getTaskcompleted() {
//        return taskcompleted;
//    }
//
//    public void setTaskcompleted(Boolean taskcompleted) {
//        this.taskcompleted = taskcompleted;
//    }

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

    public String getTasktitle() {
        return tasktitle;
    }

    public void setTasktitle(String tasktitle) {
        this.tasktitle = tasktitle;
    }

    public long getTaskscheduled() {
        return taskscheduled;
    }

    public void setTaskscheduled(long taskscheduled) {
        this.taskscheduled = taskscheduled;
    }

    public String getTasktext() {
        return tasktext;
    }

    public void setTasktext(String tasktext) {
        this.tasktext = tasktext;
    }

    public long getTasksetat() {
        return tasksetat;
    }

    public void setTasksetat(long tasksetat) {
        this.tasksetat = tasksetat;
    }

    public boolean isTaskcompleted() {
        return taskcompleted;
    }

    public void setTaskcompleted(boolean taskcompleted) {
        this.taskcompleted = taskcompleted;
    }

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }
}
