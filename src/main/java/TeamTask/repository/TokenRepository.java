package TeamTask.repository;

import TeamTask.models.User;
import TeamTask.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {


    @Query("SELECT token from Token token where token.token =(:token)")
    Token findByToken(String token);


}
