package TeamTask.repository;

import TeamTask.models.Task;
//import TeamTask.models.Teams;
import TeamTask.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT ut.taskid from TeamTask ut where ut.id_team =(:id_team)")
    List<UUID> getTasksIDsInTeam(UUID id_team);

    @Query("SELECT task from Task task where taskid =(:taskid)")
    Task getTaskOnID(UUID taskid);



}
