package TeamTask.models.dto;

public class FaceOrAppleLoginRequest {

    private String id_user;
    private String token;

    public FaceOrAppleLoginRequest(String id_user, String token) {
        this.id_user = id_user;
        this.token = token;
    }

    public FaceOrAppleLoginRequest() {
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
