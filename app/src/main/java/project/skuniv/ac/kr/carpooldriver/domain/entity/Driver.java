package project.skuniv.ac.kr.carpooldriver.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Driver implements Serializable {

    @SerializedName("lno")
    private Long lno;

    @SerializedName("licenseNumber")
    private String licenseNumber;
    @SerializedName("licenseType")
    private String licenseType;
    @SerializedName("licenseClosingDate")
    private String licenseClosingDate;

    @SerializedName("carNumber")
    private String carNumber;
    @SerializedName("carType")
    private String carType;

    @SerializedName("isInsurance")
    private boolean isInsurance;

    @SerializedName("user")
    private User user;

}
