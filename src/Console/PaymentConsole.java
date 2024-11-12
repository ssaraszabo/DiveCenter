package Console;

import Controller.PaymentController;
import Domain.Payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PaymentConsole {
    private PaymentController paymentController;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;

    public PaymentConsole(PaymentController paymentController) {
        this.paymentController = paymentController;
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void showMenu() {
        while (true) {
            System.out.println("Payment Management");
            System.out.println("1. Add Payment");
            System.out.println("2. Update Payment");
            System.out.println("3. Delete Payment");
            System.out.println("4. View Payment");
            System.out.println("5. View All Payments");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addPayment();
                    break;
                case 2:
                    updatePayment();
                    break;
                case 3:
                    deletePayment();
                    break;
                case 4:
                    viewPayment();
                    break;
                case 5:
                    viewAllPayments();
                    break;
                case 6:
                    System.out.println("Exiting Payment Management.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addPayment() {
        System.out.print("Enter Payment ID: ");
        int paymentID = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Membership ID: ");
        int membershipID = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Date paymentDate = getDateFromUser("Enter Payment Date (YYYY-MM-DD): ");
        if (paymentDate == null) return;

        if (paymentController.addPayment(paymentID, membershipID, amount, paymentDate)) {
            System.out.println("Payment added successfully.");
        } else {
            System.out.println("Failed to add payment.");
        }
    }

    private void updatePayment() {
        System.out.print("Enter Payment ID to update: ");
        int paymentID = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Membership ID: ");
        int membershipID = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter New Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Date paymentDate = getDateFromUser("Enter New Payment Date (YYYY-MM-DD): ");
        if (paymentDate == null) return;

        if (paymentController.updatePayment(paymentID, membershipID, amount, paymentDate)) {
            System.out.println("Payment updated successfully.");
        } else {
            System.out.println("Failed to update payment.");
        }
    }

    private void deletePayment() {
        System.out.print("Enter Payment ID to delete: ");
        int paymentID = Integer.parseInt(scanner.nextLine());

        if (paymentController.deletePayment(paymentID)) {
            System.out.println("Payment deleted successfully.");
        } else {
            System.out.println("Failed to delete payment.");
        }
    }

    private void viewPayment() {
        System.out.print("Enter Payment ID to view: ");
        int paymentID = Integer.parseInt(scanner.nextLine());

        Payment payment = paymentController.getPayment(paymentID);
        if (payment != null) {
            System.out.println("Payment Details: " + payment);
        } else {
            System.out.println("Payment not found.");
        }
    }

    private void viewAllPayments() {
        List<Payment> payments = paymentController.getAllPayments();
        if (payments.isEmpty()) {
            System.out.println("No payments available.");
        } else {
            System.out.println("All Payments:");
            for (Payment payment : payments) {
                System.out.println(payment);
            }
        }
    }

    private Date getDateFromUser(String prompt) {
        System.out.print(prompt);
        try {
            return dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return null;
        }
    }
}

