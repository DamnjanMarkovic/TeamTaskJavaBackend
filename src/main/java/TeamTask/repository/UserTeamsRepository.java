package TeamTask.repository;

import TeamTask.models.UserTeams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserTeamsRepository extends JpaRepository<UserTeams, Integer> {

    @Query("SELECT ut.id_user from UserTeams ut where id_team =(:id_team)")
    List<UUID> getUserIDsOnTeamID(UUID id_team);

}
