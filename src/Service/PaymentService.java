package Service;

import Domain.Payment;
import Repository.FileRepository;
import Repository.IRepository;

import java.util.Date;
import java.util.List;

public class PaymentService {
    private IRepository<Payment> paymentRepository;

    public PaymentService(IRepository<Payment> paymentRepository) {

        //this.paymentRepository = paymentRepository;
        /**
         * Initializes a new instance of PaymentService with a FileRepository.
         */
        this.paymentRepository = new FileRepository<>(
                "payments.txt",
                Payment::getPaymentID,
                line -> {
                    String[] parts = line.split(",");
                    return new Payment(
                            Integer.parseInt(parts[0]),     //paymentID
                            Integer.parseInt(parts[1]),     //membershipID
                            Double.parseDouble(parts[2]),   //amount
                            new Date(Long.parseLong(parts[3])) //paymentDate (as timestamp)
                    );
                },
                payment -> String.join(",",
                        String.valueOf(payment.getPaymentID()),
                        String.valueOf(payment.getMembershipID()),
                        String.valueOf(payment.getAmount()),
                        String.valueOf(payment.getPaymentDate().getTime())
                )
        );
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

