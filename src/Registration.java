import java.util.Date;

public class Registration {
    private int registrationID;
    private Date registrationDate;
    private String status;

    public Registration(int registrationID, Date registrationDate, String status) {
        this.registrationID = registrationID;
        this.registrationDate = registrationDate;
        this.status = status;
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

    @Override
    public String toString() {
        return "Registration [registrationID=" + registrationID + ", registrationDate=" + registrationDate +
                ", status=" + status + "]";
    }
}
