package TeamTask.controler;

//import TeamTask.models.Task;
//import TeamTask.models.User;
import TeamTask.models.dto.TaskReceiving;
import TeamTask.models.dto.TaskResponse;
//import TeamTask.models.dto.UserResponse;
import TeamTask.service.ImagesService;
import TeamTask.service.TaskService;
//import org.springframework.transaction.annotation.Transactional;
import TeamTask.service.TeamTaskService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/rest/tasks")


public class TaskController {

    private final TaskService taskService;
    private final ImagesService imagesService;
    private final TeamTaskService teamTaskService;


    public TaskController(TaskService taskService, ImagesService imagesService, TeamTaskService teamTaskService) {
        this.taskService = taskService;
        this.imagesService = imagesService;
        this.teamTaskService = teamTaskService;
    }

    @GetMapping(value = "/all")
    public List<TaskResponse> getAll() {
        return taskService.getAll();
    }

    @GetMapping(value = "/{idTeam}")
    public List<TaskResponse> getTasksInTeam(@PathVariable UUID idTeam) throws EntityNotFoundException {
        return taskService.getTasksInTeam(idTeam);
    }

    @PostMapping("/save")
    public String save(@RequestBody TaskReceiving taskReceiving){
        taskService.save(taskReceiving);
        return "Order inserted";
    }

    @DeleteMapping("/delete/{taskid}")
    public void deleteTask (@PathVariable UUID taskid) throws Exception {
        taskService.deleteTask(taskid);


    }

}
