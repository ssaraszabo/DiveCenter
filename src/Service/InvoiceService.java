package Service;

import Domain.Invoice;
import Repository.FileRepository;
import Repository.IRepository;

import java.util.Date;
import java.util.List;

public class InvoiceService {
    private IRepository<Invoice> invoiceRepository;

    public InvoiceService(IRepository<Invoice> invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
        /**
         * Initializes a new instance of InvoiceService with a FileRepository and DBRepository.
         */
//        this.invoiceRepository = new FileRepository<>(
//                "invoices.txt",
//                Invoice::getInvoiceId,
//                line -> {
//                    String[] parts = line.split(",");
//                    return new Invoice(
//                            Integer.parseInt(parts[0]),         //invoiceId
//                            Integer.parseInt(parts[1]),         //amount
//                            Boolean.parseBoolean(parts[2]),     //payment
//                            new Date(Long.parseLong(parts[3])) //issueDate(as timestamp)
//                    );
//                },
//                invoice -> String.join(",",
//                        String.valueOf(invoice.getInvoiceId()),
//                        String.valueOf(invoice.getAmount()),
//                        String.valueOf(invoice.getPayed()),
//                        String.valueOf(invoice.getIssueDate().getTime())
//                )
//        );
    }

    public boolean addInvoice(Invoice invoice) {
        return invoiceRepository.create(invoice);
    }

    /**
     * Updates an invoice.
     *
     * @param invoice The invoice that is to be updated.
     */
    public boolean updateInvoice(Invoice invoice) {
        return invoiceRepository.update(invoice);
    }

    /**
     * Deletes an invoice.
     *
     * @param invoiceID ID of the invoice that will be deleted.
     */
    public boolean deleteInvoice(int invoiceID) {
        return invoiceRepository.delete(invoiceID);
    }


    /**
     * Retrieves an invoice by ID.
     *
     * @param invoiceID ID of the invoice to be retrieved.
     * @return Invoice with the specified ID.
     */
    public Invoice getInvoice(int invoiceID) {
        return invoiceRepository.read(invoiceID);
    }

    /**
     * Retrieves all invoices.
     *
     * @return List of all invoices.
     */
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.readAll();
    }
}
