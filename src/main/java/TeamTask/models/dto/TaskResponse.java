package TeamTask.models.dto;

import java.io.Serializable;
import java.util.UUID;

public class TaskResponse implements Serializable {

    private UUID taskid;
    private String tasktitle;
    private long taskscheduled;
    private String tasktext;
    private long tasksetat;
    private boolean taskcompleted;
    private UsersInTeamResponse user;

    public TaskResponse(UUID taskid, String tasktitle, long taskscheduled, String tasktext, long tasksetat, boolean taskcompleted, UsersInTeamResponse user) {
        this.taskid = taskid;
        this.tasktitle = tasktitle;
        this.taskscheduled = taskscheduled;
        this.tasktext = tasktext;
        this.tasksetat = tasksetat;
        this.taskcompleted = taskcompleted;
        this.user = user;
    }

    public TaskResponse() {

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

    public UsersInTeamResponse getUser() {
        return user;
    }

    public void setUser(UsersInTeamResponse user) {
        this.user = user;
    }
}
