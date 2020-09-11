package TeamTask.service;

import TeamTask.models.Role;
import TeamTask.models.Task;
import TeamTask.models.User;
import TeamTask.models.dto.TaskResponse;
import TeamTask.models.dto.UserResponse;
import TeamTask.models.dto.UsersInTeamResponse;
import TeamTask.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public List<TaskResponse> getAll(){
        List<Task> taskList = taskRepository.findAll();
        return convertTaskToTasksResponse(taskList);

    }
    @Transactional
    public List<TaskResponse> getTasksInTeam(UUID idTeam) {
        List<UUID> taskUUIDs = taskRepository.getTasksIDsInTeam(idTeam);
        List<Task> alltasks = new ArrayList<>();
        for (UUID uuidTask: taskUUIDs) {
            Task task = taskRepository.getTaskOnID(uuidTask);
            alltasks.add(task);
        }
        return convertTaskToTasksResponse(alltasks);
    }


    public List<TaskResponse> convertTaskToTasksResponse(List<Task> taskList){
        List<TaskResponse> listTaskResponse = new ArrayList<>();

        for (Task task : taskList) {
            UsersInTeamResponse usersInTeamResponse = new UsersInTeamResponse(task.getUser().getId(), task.getUser().getUserFirstName(), task.getUser().getImages().getId_image(), task.getUser().getUseremail());
            TaskResponse taskResponse = new TaskResponse(task.getTaskid(), task.getTasktitle(), convertTimeToMiliseconds(task.getTaskscheduled()), task.getTasktext(), convertTimeToMiliseconds(task.getTasksetat()), task.isTaskcompleted(), usersInTeamResponse);
            listTaskResponse.add(taskResponse);
        }
        return lineUpListAccordingToTaskTimeSetAt(listTaskResponse);
    }

    List<TaskResponse> lineUpListAccordingToTaskTimeSetAt(List<TaskResponse> listTasks) {
        List<TaskResponse> finaList = new ArrayList<>();

        for (int i = 0; i < listTasks.size(); i++) {
            for (int j = i+1; j < listTasks.size() ; j++) {
                TaskResponse fake;
                if (listTasks.get(i).getTasksetat() > listTasks.get(j).getTasksetat()) {
                    fake = listTasks.get(j);
                    listTasks.set(j, listTasks.get(i));
                    listTasks.set(i, fake);
                }
            }
        }
        return listTasks;
    }



    long convertTimeToMiliseconds (String timeReceived) {       //2020-09-11 16:43:35.368929+02
       String forFormating = timeReceived.substring(0, timeReceived.length()-10);
        return LocalDateTime.parse(forFormating, DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss"))
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }




}
