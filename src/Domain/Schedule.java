package Domain;

import java.util.Date;

public class Schedule {
    private int scheduleID;
    private int employeeID;
    private Date startTime;
    private Date endTime;

    public Schedule(int scheduleID, int employeeID, Date startTime, Date endTime) {
        this.scheduleID = scheduleID;
        this.employeeID = employeeID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleID=" + scheduleID +
                ", employeeID=" + employeeID +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

