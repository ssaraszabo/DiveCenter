package Service;

import Domain.Payment;
import Repository.IRepository;

import java.util.List;

public class PaymentService {
    private IRepository<Payment> paymentRepository;

    public PaymentService(IRepository<Payment> paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public boolean addPayment(Payment payment) {
        return paymentRepository.create(payment);
    }

    public boolean updatePayment(Payment payment) {
        return paymentRepository.update(payment);
    }

    public boolean deletePayment(int paymentID) {
        return paymentRepository.delete(paymentID);
    }

    public Payment getPayment(int paymentID) {
        return paymentRepository.read(paymentID);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.readAll();
    }
}

