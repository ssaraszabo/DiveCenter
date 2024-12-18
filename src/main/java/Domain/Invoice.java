package Domain;

import java.util.Date;

public class Invoice {
    private int invoiceId;
    private int amount;
    private boolean payed;
    private Date issueDate;

    public Invoice(int invoiceId, int amount, boolean payed, Date issueDate) {
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.payed = false;
        this.issueDate = issueDate;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setPayed(boolean payed) { this.payed = payed; }

    public boolean getPayed() { return payed; }

    @Override
    public String toString() {
        return "Domain.Invoice [invoiceId=" + invoiceId + ", amount=" + amount + ", issueDate=" + issueDate + ", payed=" + payed + "]";
    }
}
