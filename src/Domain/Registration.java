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

    public static int getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(int registrationID) {
        this.registrationID = registrationID;
    }

    public static Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public static String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public static Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static Invoice getInvoice() {
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
