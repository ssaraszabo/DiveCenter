package Service;

import Domain.Invoice;
import Repository.FileRepository;
import Repository.IRepository;

import java.util.Date;
import java.util.List;

public class InvoiceService {
    private IRepository<Invoice> invoiceRepository;

    public InvoiceService(IRepository<Invoice> invoiceRepository) {
        //this.invoiceRepository = invoiceRepository;
        this.invoiceRepository = new FileRepository<>(
                "invoices.txt",
                Invoice::getInvoiceId,
                line -> {
                    String[] parts = line.split(",");
                    return new Invoice(
                            Integer.parseInt(parts[0]), //invoiceId
                            Integer.parseInt(parts[1]), //amount
                            new Date(Long.parseLong(parts[2])) //issueDate(as timestamp)
                    );
                },
                invoice -> String.join(",",
                        String.valueOf(invoice.getInvoiceId()),
                        String.valueOf(invoice.getAmount()),
                        String.valueOf(invoice.getIssueDate().getTime())
                )
        );
    }

    public boolean updateInvoice(Invoice invoice) {
        return invoiceRepository.update(invoice);
    }

    public boolean deleteInvoice(int invoiceID) {
        return invoiceRepository.delete(invoiceID);
    }

    public Invoice getInvoice(int invoiceID) {
        return invoiceRepository.read(invoiceID);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.readAll();
    }
}
