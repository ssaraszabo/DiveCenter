package Console;

import Controller.InvoiceController;
import Domain.Invoice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InvoiceConsole {
    private InvoiceController invoiceController;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    public InvoiceConsole(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void showMenu() {
        while (true) {
            System.out.println("Invoice Management");
            System.out.println("1. Update Invoice");
            System.out.println("2. Delete Invoice");
            System.out.println("3. View Invoice");
            System.out.println("4. View All Invoices");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    updateInvoice();
                    break;
                case 2:
                    deleteInvoice();
                    break;
                case 3:
                    viewInvoice();
                    break;
                case 4:
                    viewAllInvoices();
                    break;
                case 5:
                    System.out.println("Exiting Invoice Management.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void updateInvoice() {
        System.out.print("Enter Invoice ID to update: ");
        int invoiceID = Integer.parseInt(scanner.nextLine());

        Invoice existingInvoice = invoiceController.getInvoice(invoiceID);
        if (existingInvoice == null) {
            System.out.println("Invoice not found.");
            return;
        }

        System.out.print("Enter New Payment Status: ");
        boolean payed = Boolean.parseBoolean(scanner.nextLine());

        existingInvoice.setPayed(payed);

        if (invoiceController.updateInvoice(existingInvoice)) {
            System.out.println("Invoice updated successfully.");
        } else {
            System.out.println("Failed to update invoice.");
        }
    }

    private void deleteInvoice() {
        System.out.print("Enter Invoice ID to delete: ");
        int invoiceID = Integer.parseInt(scanner.nextLine());

        if (invoiceController.deleteInvoice(invoiceID)) {
            System.out.println("Invoice deleted successfully.");
        } else {
            System.out.println("Failed to delete invoice.");
        }
    }

    private void viewInvoice() {
        System.out.print("Enter Invoice ID to view: ");
        int invoiceID = Integer.parseInt(scanner.nextLine());

        Invoice invoice = invoiceController.getInvoice(invoiceID);
        if (invoice != null) {
            System.out.println("Invoice Details: " + invoice);
        } else {
            System.out.println("Invoice not found.");
        }
    }

    private void viewAllInvoices() {
        List<Invoice> invoices = invoiceController.getAllInvoices();
        if (invoices.isEmpty()) {
            System.out.println("No invoices available.");
        } else {
            System.out.println("All Invoices:");
            for (Invoice invoice : invoices) {
                System.out.println(invoice);
            }
        }
    }
}
