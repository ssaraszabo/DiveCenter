package Controller;

import Domain.Payment;
import Service.PaymentService;

import java.util.Date;
import java.util.List;

public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean addPayment(int paymentID, int membershipID, double amount, Date paymentDate) {
        if (amount <= 0) {
            System.out.println("Payment amount must be positive.");
            return false;
        }

        Payment payment = new Payment(paymentID, membershipID, amount, paymentDate);
        return paymentService.addPayment(payment);
    }

    public boolean updatePayment(int paymentID, int membershipID, double amount, Date paymentDate) {
        if (amount <= 0) {
            System.out.println("Payment amount must be positive.");
            return false;
        }

        Payment payment = new Payment(paymentID, membershipID, amount, paymentDate);
        return paymentService.updatePayment(payment);
    }

    public boolean deletePayment(int paymentID) {
        return paymentService.deletePayment(paymentID);
    }

    public Payment getPayment(int paymentID) {
        return paymentService.getPayment(paymentID);
    }

    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}

