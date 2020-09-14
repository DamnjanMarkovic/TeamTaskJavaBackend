package TeamTask.repository;

import TeamTask.models.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, UUID> {

        @Query("SELECT task.id_user_task from UserTask task where task.id_user =(:id_user)")
        List<UUID> getidUserTasksOnUserID(UUID id_user);

        @Query("SELECT taskid from UserTask task where id_user =(:id_user)")
        List<UUID> getTaskIDsOnUserID(UUID id_user);

}
