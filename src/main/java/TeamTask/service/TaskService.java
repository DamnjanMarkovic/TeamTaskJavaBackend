package TeamTask.service;

import TeamTask.models.Task;
import TeamTask.models.TeamTask;
import TeamTask.models.UserTask;
import TeamTask.models.dto.TaskReceiving;
import TeamTask.models.dto.TaskResponse;
import TeamTask.models.dto.UsersInTeamResponse;
import TeamTask.repository.TaskRepository;
import TeamTask.repository.TeamTaskRepository;
import TeamTask.repository.UserTaskRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TeamTaskRepository teamTaskRepository;
    private final TeamTaskService teamTaskService;
    private final UserTaskRepository userTaskRepository;

    public TaskService(TaskRepository taskRepository, TeamTaskRepository teamTaskRepository, TeamTaskService teamTaskService, UserTaskRepository userTaskRepository) {
        this.taskRepository = taskRepository;
        this.teamTaskRepository = teamTaskRepository;
        this.teamTaskService = teamTaskService;
        this.userTaskRepository = userTaskRepository;
    }

    @Transactional
    public String save(TaskReceiving taskReceiving){

        Task task = new Task(taskReceiving.getTaskid(), taskReceiving.getTasktitle(), convertMilisecondsToTime(taskReceiving.getTaskscheduled()),
                taskReceiving.getTasktext(), convertMilisecondsToTime(taskReceiving.getTasksetat()), taskReceiving.isTaskcompleted());

        Task taskenetered = taskRepository.save(task);
        UserTask userTask = new UserTask(taskReceiving.getId_user(), taskenetered.getTaskid());

        TeamTask teamTask = new TeamTask(taskReceiving.getId_team(), taskenetered.getTaskid());

        userTaskRepository.save(userTask);
        teamTaskRepository.save(teamTask);

       return "Completed";
    }
    @Transactional
    public void deleteTask(UUID taskid)  {
        taskRepository.deleteById(taskid);
    }

    @Transactional
    public List<TaskResponse> getAll(){
        List<Task> taskList = taskRepository.findAll();
        return convertTaskToTasksResponse(taskList);

    }

    @Transactional
    public void deleteTasksOnUserID(UUID id_user){
        List<UUID> listTaskIDs = userTaskRepository.getTaskIDsOnUserID(id_user);
        for (UUID taskid:listTaskIDs             ) {
            System.out.println("stigli ovde");
            Task task = new Task(taskid);
            taskRepository.delete(task);
        }

    }
    public List<UUID> returnTasksOnUserID(UUID id_user){
        return userTaskRepository.getTaskIDsOnUserID(id_user);

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
            UsersInTeamResponse usersInTeamResponse = new UsersInTeamResponse(task.getUser().getId(), task.getUser().getUserFirstName(), task.getUser().getImages().getId_image());
            TaskResponse taskResponse = new TaskResponse(task.getTaskid(), task.getTasktitle(), convertTimeToMiliseconds(task.getTaskscheduled()), task.getTasktext(), convertTimeToMiliseconds(task.getTasksetat()), task.isTaskcompleted(), usersInTeamResponse);
            listTaskResponse.add(taskResponse);
        }
        return lineUpListAccordingToTaskTimeSetAt(listTaskResponse);
    }

    List<TaskResponse> lineUpListAccordingToTaskTimeSetAt(List<TaskResponse> listTasks) {
//        List<TaskResponse> finaList = new ArrayList<>();

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



    long convertTimeToMiliseconds (Timestamp timeReceived) {       //2020-09-11 16:43

        String timeFinal = String.valueOf(timeReceived);

       String forFormating = timeFinal.substring(0, timeFinal.length()-5);
        return LocalDateTime.parse(forFormating, DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"))
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

//    Timestamp convertMilisecondsToTime (Double timeReceived) {       //2020-09-11 16:43
//
//        String timeFinal = String.valueOf(timeReceived);
//
//        String forFormating = timeFinal.substring(0, timeFinal.length()-10);
//        return LocalDateTime.parse(forFormating, DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"))
//                .atZone(ZoneId.systemDefault())
//                .toInstant()
//                .toEpochMilli();
//    }

    public Timestamp convertMilisecondsToTime(long timeReceived) {
        String timestampInString = String.valueOf(timeReceived);
        if (StringUtils.isNotBlank(timestampInString) && timestampInString != null) {
            Date date = new Date(Long.parseLong(timestampInString));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            String formatted = format.format(date);
            Timestamp timeStamp = Timestamp.valueOf(formatted);
            return timeStamp;
        } else {
            return null;
        }
    }


}
