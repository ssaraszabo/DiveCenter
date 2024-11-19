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

    /**
     * Adds a new payment to the repository.
     *
     * @param payment The payment to add.
     */
    public boolean addPayment(Payment payment) {
        return paymentRepository.create(payment);
    }

    /**
     * Updates a payment.
     *
     * @param payment The payment to be updated.
     */
    public boolean updatePayment(Payment payment) {
        return paymentRepository.update(payment);
    }

    /**
     * Deletes a payment from the repository.
     *
     * @param paymentID The schedule to add.
     */
    public boolean deletePayment(int paymentID) {
        return paymentRepository.delete(paymentID);
    }

    /**
     * Retrieves a payment by ID.
     *
     * @param paymentID ID of the wanted payment.
     * @return The payment with the specified ID.
     */
    public Payment getPayment(int paymentID) {
        return paymentRepository.read(paymentID);
    }

    /**
     * Retrieves all payments.
     *
     * @return List of all payments.
     */
    public List<Payment> getAllPayments() {
        return paymentRepository.readAll();
    }
}

