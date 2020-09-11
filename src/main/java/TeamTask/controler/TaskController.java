package TeamTask.controler;

//import TeamTask.models.Task;
//import TeamTask.models.User;
import TeamTask.models.dto.TaskResponse;
//import TeamTask.models.dto.UserResponse;
import TeamTask.service.ImagesService;
import TeamTask.service.TaskService;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/rest/tasks")


public class TaskController {

    private final TaskService taskService;
    private final ImagesService imagesService;


    public TaskController(TaskService taskService, ImagesService imagesService) {
        this.taskService = taskService;
        this.imagesService = imagesService;
    }

    @GetMapping(value = "/all")
    public List<TaskResponse> getAll() {
        return taskService.getAll();
    }

    @GetMapping(value = "/{idTeam}")
    public List<TaskResponse> getTasksInTeam(@PathVariable UUID idTeam) throws EntityNotFoundException {
        return taskService.getTasksInTeam(idTeam);
    }


}
