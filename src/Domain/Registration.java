package Domain;

import java.util.Date;

public class Registration {
    private int registrationID;
    private Date registrationDate;
    private String status;
    private Client client;
    private Course course;
    private Invoice invoice;

    public Registration(int registrationID, Date registrationDate, String status, Client client, Course course, Invoice invoice) {
        this.registrationID = registrationID;
        this.registrationDate = registrationDate;
        this.status = status;
        this.client = client;
        this.course = course;
        this.invoice = invoice;
    }

    public int getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(int registrationID) {
        this.registrationID = registrationID;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registrationID=" + registrationID +
                ", registrationDate=" + registrationDate +
                ", status='" + status + '\'' +
                ", client=" + client +
                ", course=" + course +
                ", invoice=" + invoice +
                '}';
    }
}
