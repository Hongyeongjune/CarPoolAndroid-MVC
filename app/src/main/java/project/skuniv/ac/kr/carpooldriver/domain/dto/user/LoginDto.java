package project.skuniv.ac.kr.carpooldriver.domain.dto.user;

public class LoginDto {

    private String message;

    public LoginDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
