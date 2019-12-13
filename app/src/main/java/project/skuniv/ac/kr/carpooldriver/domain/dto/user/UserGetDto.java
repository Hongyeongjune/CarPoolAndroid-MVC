package project.skuniv.ac.kr.carpooldriver.domain.dto.user;

public class UserGetDto {

    private Long uno;
    private String id;
    private String name;
    private String age;
    private String sex;
    private String phone;

    public UserGetDto() {
    }

    public UserGetDto(Long uno, String id, String name, String age, String sex, String phone) {
        this.uno = uno;
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
    }

    public Long getUno() {
        return uno;
    }

    public void setUno(Long uno) {
        this.uno = uno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
