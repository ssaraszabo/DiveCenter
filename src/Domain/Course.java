package Domain;

import java.util.Date;

public class Course {
    private int courseID;
    private String name;
    private Date startTime;
    private int minAge;
    private String experienceRequired;
    private int maxCapacity;
    private int currentCapacity;

    public Course(int courseID, String name, Date startTime, int minAge, String experienceRequired, int maxCapacity, int currentCapacity) {
        this.courseID = courseID;
        this.name = name;
        this.startTime = startTime;
        this.minAge = minAge;
        this.experienceRequired = experienceRequired;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public String getExperienceRequired() {
        return experienceRequired;
    }

    public void setExperienceRequired(String experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    @Override
    public String toString() {
        return "Domain.Course [courseID=" + courseID + ", name=" + name + ", startTime=" + startTime + ", minAge=" + minAge +
                ", experienceRequired=" + experienceRequired + ", maxCapacity=" + maxCapacity + ", currentCapacity=" +
                currentCapacity + "]";
    }
}
