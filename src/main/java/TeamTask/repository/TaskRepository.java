package TeamTask.repository;

import TeamTask.models.Task;
//import TeamTask.models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {



}
