package project.skuniv.ac.kr.carpooldriver.domain.dto.driving;

import java.util.Date;

public class DrivingSaveDto {

    private String departure;
    private String destination;
    private String distance;
    private String date;
    private String userId;

    public DrivingSaveDto(String departure, String destination, String distance, String date, String userId) {
        this.departure = departure;
        this.destination = destination;
        this.distance = distance;
        this.date = date;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
