package TeamTask.models.dto;

public class FaceOrAppleLoginRequest {

    private String userName;
    private String token;

    public FaceOrAppleLoginRequest(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public FaceOrAppleLoginRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
