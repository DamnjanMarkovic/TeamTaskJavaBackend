package TeamTask.repository;

import TeamTask.models.Task;
//import TeamTask.models.Teams;
import TeamTask.models.User;
import TeamTask.models.dto.TaskResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT ut.taskid from TeamTask ut where ut.id_team =(:id_team)")
    List<UUID> getTasksIDsInTeam(UUID id_team);

    @Query("SELECT task from Task task where taskid =(:taskid)")
    Task getTaskOnID(UUID taskid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO team_task (id_team, taskid) VALUES (?, ?)",
            nativeQuery = true)
    void setIDTeamInTeamTask(UUID id_team, UUID taskid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_task (id_user, taskid) VALUES (?, ?)",
            nativeQuery = true)
    void setIDUserInUserTask(UUID id_user, UUID taskid);





}
