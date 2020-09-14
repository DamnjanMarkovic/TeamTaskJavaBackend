package TeamTask.repository;

import TeamTask.models.UserTeams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTeamsRepository extends JpaRepository<UserTeams, Integer> {


}
