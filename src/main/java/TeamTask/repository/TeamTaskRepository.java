package TeamTask.repository;

import TeamTask.models.TeamTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamTaskRepository extends JpaRepository<TeamTask, UUID> {

    @Query("SELECT ut.id_team_task from TeamTask ut where id_team =(:id_team)")
    List<UUID> getteamtaskOnTeamID(UUID id_team);

}
