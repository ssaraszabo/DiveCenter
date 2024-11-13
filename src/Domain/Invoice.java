package Domain;

import java.util.Date;

public class Invoice {
    private int invoiceId;
    private int amount;
    private Date issueDate;

    public Invoice(int invoiceId, int amount, Date issueDate) {
        this.invoiceId = invoiceId;
        this.amount = amount;
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

    @Override
    public String toString() {
        return "Domain.Invoice [invoiceId=" + invoiceId + ", amount=" + amount + ", issueDate=" + issueDate + "]";
    }
}
