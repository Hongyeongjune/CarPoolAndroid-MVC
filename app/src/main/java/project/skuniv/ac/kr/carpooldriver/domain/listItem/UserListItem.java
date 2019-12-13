package project.skuniv.ac.kr.carpooldriver.domain.listItem;

import java.time.LocalDate;

public class UserListItem {

    private Long dno;
    private String distance;
    private String departure;
    private String destination;
    private String date;
    private LocalDate regDate;

    public UserListItem() {
    }

    public UserListItem(Long dno, String distance, String departure, String destination, String date, LocalDate regDate) {
        this.dno = dno;
        this.distance = distance;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.regDate = regDate;
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

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }


}
