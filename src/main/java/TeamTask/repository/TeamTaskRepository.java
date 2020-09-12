package TeamTask.repository;

import TeamTask.models.TeamTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamTaskRepository extends JpaRepository<TeamTask, UUID> {


}
