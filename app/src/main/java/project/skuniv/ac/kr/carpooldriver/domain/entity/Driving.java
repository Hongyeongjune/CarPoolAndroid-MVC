package project.skuniv.ac.kr.carpooldriver.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Driving implements Serializable {

    @SerializedName("dno")
    private Long dno;

    @SerializedName("departure")
    private String departure;
    @SerializedName("destination")
    private String destination;
    @SerializedName("distance")
    private String distance;
    @SerializedName("date")
    private Date date;
    @SerializedName("isDriving")
    private Boolean isDriving;
    @SerializedName("regDate")
    private LocalDate regDate;

    @SerializedName("user")
    private User user;

    @SerializedName("driver")
    private User driver;
}
