package TeamTask.repository;


import TeamTask.models.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRolesRepository extends JpaRepository<UserRoles, UUID> {
}
