package Service;

import Domain.*;
import Repository.IRepository;

import java.util.Date;
import java.util.List;

public class RegistrationService {
    private IRepository<Registration> registrationRepository;

    public RegistrationService(IRepository<Registration> registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public Registration createRegistration(int registrationID, Client client, Course course, int amount) throws IllegalStateException {
        if (!isEligible(client, course)) {
            throw new IllegalStateException("Client does not meet the age or experience requirements for the course.");
        }

        if (!course.hasAvailability()) {
            throw new IllegalStateException("Course is at full capacity.");
        }

        course.incrementCapacity();

        Date registrationDate = new Date();
        int invoiceID = generateInvoiceID();
        Invoice invoice = new Invoice(invoiceID, amount, registrationDate);

        String status = "Active";

        Registration registration = new Registration(registrationID, registrationDate, status, client, course, invoice);

        registrationRepository.create(registration);

        return registration;
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.readAll();
    }

    public void updateRegistration(Registration registration) {
        registrationRepository.update(registration);
    }

    public void deleteRegistration(int registrationID) {
        Registration registrationToDelete = registrationRepository.read(registrationID);
        if (registrationToDelete != null) {

            Course course = registrationToDelete.getCourse();
            course.decrementCapacity();

            registrationRepository.delete(registrationID);
        }
    }

    public void updateRegistration(int registrationID, Date newTime) {
        Registration registration = registrationRepository.read(registrationID);
        if (registration != null) {
            registration.setRegistrationDate(newTime);
            registrationRepository.update(registration);
        }
    }

    private int generateInvoiceID() {
        return registrationRepository.readAll().size() + 1;
    }

    private boolean isEligible(Client client, Course course) {
        if (client.getAge() < course.getMinAge()) {
            return false;
        }

        return client.getexperienceLevel().equalsIgnoreCase(course.getExperienceRequired()) ||
                course.getExperienceRequired().isEmpty(); // Allow if experience requirement is not specified
    }
    public Registration getRegistration(int id) {
        return registrationRepository.read(id);
    }
}

