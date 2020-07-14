package project.skuniv.ac.kr.carpooldriver.domain.dto.fcm;

public class SendDto {

    private String deviceToken;

    public SendDto(String deviceToken, String userId, String title, String body) {
        this.deviceToken = deviceToken;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    private String userId;
    private String title;
    private String body;

    public SendDto(String title, String body, String userId) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
