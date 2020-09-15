package TeamTask.controler;


import TeamTask.models.Teams;
import TeamTask.service.TaskService;
import TeamTask.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import TeamTask.models.Images;
import org.springframework.web.bind.annotation.*;
import TeamTask.service.ImagesService;
import TeamTask.service.TeamService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/rest/teams")
public class TeamController {


    private final TeamService teamService;
    private final ImagesService imagesService;
    private final UserService userService;
    private final TaskService taskService;

    public TeamController(TeamService teamService, ImagesService imagesService, UserService userService, TaskService taskService) {
        this.teamService = teamService;
        this.imagesService = imagesService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Teams>> getAll(){
        List<Teams> teams;
            teams = teamService.getAll();

        return new ResponseEntity<>(teams, HttpStatus.OK);

    }

    @GetMapping("/ifExists/{id_team}")
    public String checkIfTeamExists(@PathVariable UUID id_team)  {
        return teamService.checkIfTeamExists(id_team);
    }


    @GetMapping("/{id}")
    public Optional<Teams> getRestaurant(@PathVariable Integer id) throws Exception {
        try {
            return teamService.getRestaurant(id);
        } catch (Exception e){
            throw new Exception("poruka", e.initCause(e.getCause()));
        }
    }

    @DeleteMapping("/deleteTeam/{id_team}")
    public void deleteTeam (@PathVariable UUID id_team) throws Exception {
        userService.deleteUsersInTeam(id_team);
        teamService.deleteTeam(id_team);


    }


    @PostMapping(value = "/loadRestaurant", consumes = {"multipart/form-data"})
    public String saveUser (@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
                            Teams teams){
        String result = null;
        String response = null;
        Images images = new Images();
        images.setImagename(imageFile.getOriginalFilename());
        try {
            Integer id_image = imagesService.saveSpecificImage(imageFile, images);
//            teams.setId_image(id_image);
            response = teamService.save(teams);
            result = response;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping(value = "/load")
    public List<Teams> persist(@RequestBody final Teams teams) throws SQLException {

        teamService.save(teams);
        return teamService.getAll();
    }


}
