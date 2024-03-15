package hbv.example.model;

import java.time.LocalDateTime;

public class Timeslot {
    private int id;
    private int centerId;
    private String startTime;
    private String endTime;
    private int capacity;

    // Constructors
    public Timeslot() {
    }

    public Timeslot(int centerId, String  startTime, String endTime, int capacity) {
        this.centerId = centerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

//    // toString method for debugging
//    @Override
//    public String toString() {
//        return "TimeSlot{" +
//                "id=" + id +
//                ", centerId=" + centerId +
//                ", startTime=" + startTime +
//                ", endTime=" + endTime +
//                ", capacity=" + capacity +
//                '}';
//    }
}
