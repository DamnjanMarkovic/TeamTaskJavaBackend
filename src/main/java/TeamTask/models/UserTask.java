package TeamTask.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_task")
public class UserTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_user_task;
    private UUID id_user;
    private UUID taskid;

    public UserTask(UUID id_user_task, UUID id_user, UUID taskid) {
        this.id_user_task = id_user_task;
        this.id_user = id_user;
        this.taskid = taskid;
    }
    public UserTask() {
    }

    public UserTask(UUID taskid) {
        this.taskid = taskid;
    }

    public UserTask(UserTask userTask) {
        this.id_user_task = userTask.getId_user_task();
        this.id_user = userTask.getId_user();
        this.taskid = userTask.getTaskid();
    }

    public UserTask(UUID id_user, UUID taskid) {
        this.id_user = id_user;
        this.taskid = taskid;
    }

    public UUID getId_user_task() {
        return id_user_task;
    }

    public void setId_user_task(UUID id_user_task) {
        this.id_user_task = id_user_task;
    }

    public UUID getId_user() {
        return id_user;
    }

    public void setId_user(UUID id_user) {
        this.id_user = id_user;
    }

    public UUID getTaskid() {
        return taskid;
    }

    public void setTaskid(UUID taskid) {
        this.taskid = taskid;
    }
}
