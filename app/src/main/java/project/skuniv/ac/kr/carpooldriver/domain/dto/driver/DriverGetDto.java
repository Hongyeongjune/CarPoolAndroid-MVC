package project.skuniv.ac.kr.carpooldriver.domain.dto.driver;

public class DriverGetDto {

    private String licenseNumber;
    private String licenseType;
    private String licenseClosingDate;

    private String carNumber;
    private String carType;

    private boolean isInsurance;

    public DriverGetDto(String licenseNumber, String licenseType, String licenseClosingDate, String carNumber, String carType, boolean isInsurance) {
        this.licenseNumber = licenseNumber;
        this.licenseType = licenseType;
        this.licenseClosingDate = licenseClosingDate;
        this.carNumber = carNumber;
        this.carType = carType;
        this.isInsurance = isInsurance;
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
