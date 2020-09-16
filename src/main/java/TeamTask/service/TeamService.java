package TeamTask.service;



import TeamTask.models.Task;
import TeamTask.models.Teams;
import TeamTask.models.dto.TaskResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TeamTask.repository.TeamRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {


    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public List<Teams> getAll(){
        return teamRepository.findAll();
    }

    @Transactional
    public Optional<Teams> getRestaurant(Integer id)  {

//            return teamRepository.findById((id));
return null;
    }
    @Transactional
    public void deleteTeam(UUID id_team)  {
        Teams team = new Teams(id_team);
        teamRepository.delete(team);
    }

    @Transactional
    public String checkIfTeamExists(UUID id_Team) {
        String result = "";

        if (teamRepository.checkIfTeamExists(id_Team) > 0) {
            result = "Team exists!";
        } else {
            result = "That team does not exists.";
        }
        return result;
    }
    @Transactional
    public Teams getTeam(String idTeam) {
        UUID id_team = UUID.fromString(idTeam);
        return teamRepository.findById(id_team).get();

    }



    @Transactional
    public String save(Teams teams) throws SQLException {
        String result = null;

        result = "Team inserted in the DB";
        return result;
    }


}
