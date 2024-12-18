package Domain;

import java.util.Date;

public class Employee extends Person {
    private String position;
    private String employmentDate;

    public Employee(int id, String name, int age, String contactInfo, String position, String employmentDate) {
        super(id, name, age, contactInfo);
        this.position = position;
        this.employmentDate = employmentDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    @Override
    public String toString() {
        return "Domain.Employee [position=" + position + ", employmentDate=" + employmentDate + ", " + super.toString() + "]";
    }
}
