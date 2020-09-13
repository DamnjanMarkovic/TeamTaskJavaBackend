package TeamTask.repository;

import TeamTask.models.TeamTask;
import TeamTask.models.UserImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserImagesRepository extends JpaRepository<UserImages, Integer> {


}
