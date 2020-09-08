package TeamTask.service;



import TeamTask.models.Teams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TeamTask.repository.TeamRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

            return teamRepository.findById((id));

    }
    @Transactional
    public void deleteRestaurant(Integer id)  {

        teamRepository.deleteAvailableIngredientsInRestaurant(id);

        teamRepository.deleteById(id);

    }

    @Transactional
    public String save(Teams teams) throws SQLException {
        String result = null;

        result = "Team inserted in the DB";
        return result;
    }


}
