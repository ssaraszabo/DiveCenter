import java.util.Date;

public class Membership {
    private int membershipID;
    private Date startDate;
    private Date endDate;
    private String membershipType;

    public Membership(int membershipID, Date startDate, Date endDate, String membershipType) {
        this.membershipID = membershipID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.membershipType = membershipType;
    }

    public int getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(int membershipID) {
        this.membershipID = membershipID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    @Override
    public String toString() {
        return "Membership [membershipID=" + membershipID + ", startDate=" + startDate + ", endDate=" + endDate +
                ", membershipType=" + membershipType + "]";
    }
}
