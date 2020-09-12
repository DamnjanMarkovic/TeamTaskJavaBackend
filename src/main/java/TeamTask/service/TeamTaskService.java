package TeamTask.service;


import TeamTask.repository.TeamTaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamTaskService {

    private final TeamTaskRepository teamTaskRepository;

    public TeamTaskService(TeamTaskRepository teamTaskRepository) {
        this.teamTaskRepository = teamTaskRepository;
    }



}
