package Domain;

import java.util.Date;

public class Payment {
    private int paymentID;
    private int membershipID;
    private double amount;
    private Date paymentDate;

    public Payment(int paymentID, int membershipID, double amount, Date paymentDate) {
        this.paymentID = paymentID;
        this.membershipID = membershipID;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(int membershipID) {
        this.membershipID = membershipID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", membershipID=" + membershipID +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}


