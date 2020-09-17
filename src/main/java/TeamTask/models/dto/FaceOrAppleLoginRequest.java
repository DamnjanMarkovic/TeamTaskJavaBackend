package TeamTask.models.dto;

import java.util.UUID;

public class FaceOrAppleLoginRequest {

    private String userName;
    private UUID user_id;
//    private String token;

    public FaceOrAppleLoginRequest(String userName, UUID user_id) {
        this.userName = userName;
        this.user_id = user_id;
//        this.token = token;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public FaceOrAppleLoginRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
}
