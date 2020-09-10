package TeamTask.controler;

import TeamTask.service.ImagesService;
import TeamTask.service.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/tasks")


public class TaskController {

    private final TaskService taskService;
    private final ImagesService imagesService;


    public TaskController(TaskService taskService, ImagesService imagesService) {
        this.taskService = taskService;
        this.imagesService = imagesService;
    }
}
