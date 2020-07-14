package project.skuniv.ac.kr.carpooldriver.domain.dto.driver;

public class DriverRegisterDto {

    private String licenseNumber;
    private String licenseType;
    private String licenseClosingDate;

    private String carNumber;
    private String carType;

    private boolean isInsurance;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DriverRegisterDto(String licenseNumber, String licenseType, String licenseClosingDate, String carNumber, String carType, boolean isInsurance, String userId) {
        this.licenseNumber = licenseNumber;
        this.licenseType = licenseType;
        this.licenseClosingDate = licenseClosingDate;
        this.carNumber = carNumber;
        this.carType = carType;
        this.isInsurance = isInsurance;
        this.userId = userId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getLicenseClosingDate() {
        return licenseClosingDate;
    }

    public void setLicenseClosingDate(String licenseClosingDate) {
        this.licenseClosingDate = licenseClosingDate;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public boolean isInsurance() {
        return isInsurance;
    }

    public void setInsurance(boolean insurance) {
        isInsurance = insurance;
    }
}
