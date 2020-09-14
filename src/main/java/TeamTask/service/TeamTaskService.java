package TeamTask.service;


import TeamTask.repository.TeamTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TeamTaskService {

    private final TeamTaskRepository teamTaskRepository;

    public TeamTaskService(TeamTaskRepository teamTaskRepository) {
        this.teamTaskRepository = teamTaskRepository;
    }

    @Transactional
    public void deleteTeamTaskOnTeamID(UUID id_team){
        List<UUID> listteamTaskIDs = teamTaskRepository.getteamtaskOnTeamID(id_team);
        for (UUID teamtaskid:listteamTaskIDs             ) {
            teamTaskRepository.deleteById(teamtaskid);
        }
    }

    @Transactional
    public void deleteTeamTaskOnTeamID1(UUID teamtaskid){
            teamTaskRepository.deleteById(teamtaskid);
    }


}
