package project.skuniv.ac.kr.carpooldriver.domain.dto.user;

public class CheckUserIdDto {

    private String userId;

    public CheckUserIdDto(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
