package TeamTask.service;

import TeamTask.repository.UserTaskRepository;
import org.springframework.stereotype.Service;

@Service
public class UserTaskService {

    private final UserTaskRepository userTaskRepository;

    public UserTaskService(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }
}
