package project.skuniv.ac.kr.carpooldriver.domain.listItem;

public class FinishListItem {

    private Long dno;
    private String distance;
    private String departure;
    private String destination;
    private String date;
    private String regDate;
    private boolean isDriving;
    private boolean driverCall;
    private boolean driverFinishCall;
    private boolean userFinishCall;

    public FinishListItem() {
    }

    public Long getDno() {
        return dno;
    }

    public void setDno(Long dno) {
        this.dno = dno;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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
}
