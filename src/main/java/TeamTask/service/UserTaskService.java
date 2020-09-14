package TeamTask.service;

import TeamTask.repository.UserTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserTaskService {

    private final UserTaskRepository userTaskRepository;

    public UserTaskService(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }

    @Transactional
    public void deleteUserTasksOnUserID(UUID id_user){
        List<UUID> listTaskIDs = userTaskRepository.getidUserTasksOnUserID(id_user);
        for (UUID usertaskid:listTaskIDs             ) {
            userTaskRepository.deleteById(usertaskid);
        }
    }

}
