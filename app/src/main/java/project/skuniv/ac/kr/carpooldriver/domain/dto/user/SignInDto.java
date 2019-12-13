package project.skuniv.ac.kr.carpooldriver.domain.dto.user;

public class SignInDto {

    private String id;
    private String password;

    public SignInDto(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
