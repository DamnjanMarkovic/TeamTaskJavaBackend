package TeamTask.controler;

import TeamTask.models.dto.UsersInTeamResponse;
import TeamTask.service.UserTeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/rest/usersTeams")
public class UsersTeamsController {
    @Autowired
    private final UserTeamsService userTeamsService;

    public UsersTeamsController(UserTeamsService userTeamsService) {
        this.userTeamsService = userTeamsService;
    }

    @GetMapping("/getUsersInTeam/{idTeam}")
	public List<UsersInTeamResponse> getUsersInTeam(@PathVariable UUID idTeam) throws EntityNotFoundException {
		return userTeamsService.getUsersInTeam(idTeam);
	}

}
