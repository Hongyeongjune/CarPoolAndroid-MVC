package project.skuniv.ac.kr.carpooldriver.domain.dto.driving;

import java.time.LocalDate;

public class DrivingGetDto {

    private Long dno;
    private String departure;
    private String destination;
    private String distance;
    private String date;
    private String regDate;
    private String userId;
    private boolean isDriving;
    private boolean driverCall;
    private boolean driverFinishCall;
    private boolean userFinishCall;


    public DrivingGetDto(Long dno, String departure, String destination, String distance, String date, String regDate, String userId, boolean isDriving, boolean driverCall, boolean driverFinishCallCall, boolean userFinishCallCall) {
        this.dno = dno;
        this.departure = departure;
        this.destination = destination;
        this.distance = distance;
        this.date = date;
        this.regDate = regDate;
        this.userId = userId;
        this.isDriving = isDriving;
        this.driverCall = driverCall;
        this.driverFinishCall = driverFinishCallCall;
        this.userFinishCall = userFinishCallCall;
    }

    public boolean isDriving() {
        return isDriving;
    }

    public void setDriving(boolean driving) {
        isDriving = driving;
    }

    public boolean isDriverCall() {
        return driverCall;
    }

    public void setDriverCall(boolean driverCall) {
        this.driverCall = driverCall;
    }

    public boolean isDriverFinishCall() {
        return driverFinishCall;
    }

    public void setDriverFinishCall(boolean driverFinishCall) {
        this.driverFinishCall = driverFinishCall;
    }

    public boolean isUserFinishCall() {
        return userFinishCall;
    }

    public void setUserFinishCall(boolean userFinishCall) {
        this.userFinishCall = userFinishCall;
    }

    public DrivingGetDto(Long dno, String departure, String destination, String distance, String date, String regDate, String userId) {
        this.dno = dno;
        this.departure = departure;
        this.destination = destination;
        this.distance = distance;
        this.date = date;
        this.regDate = regDate;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getDno() {
        return dno;
    }

    public void setDno(Long dno) {
        this.dno = dno;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

}
