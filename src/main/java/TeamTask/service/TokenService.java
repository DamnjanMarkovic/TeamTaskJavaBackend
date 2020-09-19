package TeamTask.service;

import TeamTask.models.Token;
import TeamTask.repository.TokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Transactional
    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    @Transactional
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Transactional
    public void findByUserID(Token token) {
        tokenRepository.save(token);
    }


    @Transactional
    public void getUserOnToken(Token token) {
        tokenRepository.save(token);
    }


    @Transactional
    public void removeToken(String token) {
        tokenRepository.removeTokenOnToken(token);

    }


}
