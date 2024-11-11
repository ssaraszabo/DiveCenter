import java.util.Date;

public class Employee extends Person {
    private String position;
    private Date employmentDate;

    public Employee(int id, String name, int age, String contactInfo, String position, Date employmentDate) {
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

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    @Override
    public String toString() {
        return "Employee [position=" + position + ", employmentDate=" + employmentDate + ", " + super.toString() + "]";
    }
}
