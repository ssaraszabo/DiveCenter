//package Controller;
//
//import Domain.Invoice;
//import Service.InvoiceService;
//
//import java.util.List;
//
//public class InvoiceController {
//    private InvoiceService invoiceService;
//
//    public InvoiceController(InvoiceService invoiceService) {
//        this.invoiceService = invoiceService;
//    }
//
//    public boolean updateInvoice(Invoice invoice) {
//        return invoiceService.updateInvoice(invoice);
//    }
//
//    public boolean createInvoice(Invoice invoice) {
//        return invoiceService.addInvoice(invoice);
//    }
//
//    public boolean deleteInvoice(int invoiceID) {
//        return invoiceService.deleteInvoice(invoiceID);
//    }
//
//    public Invoice getInvoice(int invoiceID) {
//        return invoiceService.getInvoice(invoiceID);
//    }
//
//    public List<Invoice> getAllInvoices() {
//        return invoiceService.getAllInvoices();
//    }
//}
