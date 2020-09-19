package TeamTask.repository;

import TeamTask.models.User;
import TeamTask.models.Token;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Token tok where tok.token =(:token)")
    void removeTokenOnToken(String token);



    @Query("SELECT token from Token token where token.token =(:token)")
    Token findByToken(String token);


}
