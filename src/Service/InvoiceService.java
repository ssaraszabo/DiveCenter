package Service;

import Domain.Invoice;
import Repository.IRepository;

import java.util.Date;
import java.util.List;

public class InvoiceService {
    private IRepository<Invoice> invoiceRepository;

    public InvoiceService(IRepository<Invoice> invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
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
