package Service;

import Domain.*;
import Repository.IRepository;
import Repository.FileRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistrationService {
    private IRepository<Registration> registrationRepository;

    public RegistrationService(IRepository<Registration> registrationRepository) {
        /**
         * Initializes a new instance of RegistrationService with a FileRepository.
         */
        this.registrationRepository = new FileRepository<>(
                "registrations.txt",
                Registration::getRegistrationID,
                line -> {
                    String[] parts = line.split(",");
                    return new Registration(
                            Integer.parseInt(parts[0]),         //registrationID
                            new Date(Long.parseLong(parts[1])), //registrationDate (as timestamp)
                            parts[2],                           //status
                            new Client(
                                    Integer.parseInt(parts[3]), //clientID
                                    parts[4],                   //clientName
                                    Integer.parseInt(parts[5]), //clientAge
                                    parts[6],                   //clientContactInfo
                                    parts[7],                   //clientExperienceLevel
                                    Boolean.parseBoolean(parts[8]) //isMember
                            ),
                            new Course(
                                    Integer.parseInt(parts[9]),     //courseID
                                    parts[10],                      //courseName
                                    new Date(Long.parseLong(parts[11])), //courseStartTime
                                    Integer.parseInt(parts[12]),    //minAge
                                    parts[13],                      //experienceRequired
                                    Integer.parseInt(parts[14]),    //maxCapacity
                                    Integer.parseInt(parts[15])     //currentCapacity
                            ),
                            new Invoice(
                                    Integer.parseInt(parts[16]),         //invoiceId
                                    Integer.parseInt(parts[17]),         //amount
                                    Boolean.parseBoolean(parts[18]),     //payment
                                    new Date(Long.parseLong(parts[19])) //issueDate(as timestamp)
                            )
                    );
                },
                registration -> String.join(",",
                        String.valueOf(registration.getRegistrationID()),
                        String.valueOf(registration.getRegistrationDate().getTime()),
                        registration.getStatus(),
                        String.valueOf(registration.getClient().getId()),
                        registration.getClient().getName(),
                        String.valueOf(registration.getClient().getAge()),
                        registration.getClient().getContactInfo(),
                        registration.getClient().getexperienceLevel(),
                        String.valueOf(registration.getClient().isMember()),
                        String.valueOf(registration.getCourse().getCourseID()),
                        registration.getCourse().getName(),
                        String.valueOf(registration.getCourse().getStartTime().getTime()),
                        String.valueOf(registration.getCourse().getMinAge()),
                        registration.getCourse().getExperienceRequired(),
                        String.valueOf(registration.getCourse().getMaxCapacity()),
                        String.valueOf(registration.getCourse().getCurrentCapacity()),
                        String.valueOf(registration.getInvoice().getInvoiceId()),
                        String.valueOf(registration.getInvoice().getAmount()),
                        String.valueOf(registration.getInvoice().getIssueDate().getTime())
                )
        );
    }

    /**
     * Adds a new registration to the repository.
     *
     * @param registrationID, Id of registration.
     * @param client          Client that is linked to the registration.
     * @param course          Course that Client is registered for.
     * @param amount          Price of Course.
     * @return The registration.
     */
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
        boolean payed = false;
        Invoice invoice = new Invoice(invoiceID, amount, payed, registrationDate);

        String status = "Active";

        Registration registration = new Registration(registrationID, registrationDate, status, client, course, invoice);

        registrationRepository.create(registration);

        return registration;
    }

    /**
     * Retrieves all registrations.
     *
     * @return All registrations with their details.
     */
    public List<Registration> getAllRegistrations() {
        return registrationRepository.readAll();
    }

    /**
     * Deletes a registration by its ID.
     *
     * @param registrationID The ID of the registration to delete.
     */
    public void deleteRegistration(int registrationID) {
        Registration registrationToDelete = registrationRepository.read(registrationID);
        if (registrationToDelete != null) {

            Course course = registrationToDelete.getCourse();
            course.decrementCapacity();

            registrationRepository.delete(registrationID);
        }
    }

    /**
     * Updates an existing registration in the repository.
     *
     * @param registrationID The ID of the registration.
     * @param newTime        Updated time of registration.
     */
    public void updateRegistration(int registrationID, Date newTime) {
        Registration registration = registrationRepository.read(registrationID);
        if (registration != null) {
            registration.setRegistrationDate(newTime);
            registrationRepository.update(registration);
        }
    }

    /**
     * Creates Invoice for the registration.
     */
    private int generateInvoiceID() {
        return registrationRepository.readAll().size() + 1;
    }

    /**
     * Checks if a client is eligible for a course.
     *
     * @param client Client that wants to register.
     * @param course Course that client wants to register for.
     */
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

    /**
     * Retrieves a list of clients who have unpaid invoices.
     *
     * @return A list of clients with unpaid invoices.
     */
    public List<Client> getClientsWithUnpaidInvoices() {
        List<Registration> allRegistrations = registrationRepository.readAll();
        List<Client> clientsWithUnpaidInvoices = new ArrayList<>();

        for (Registration registration : allRegistrations) {
            if (!registration.getInvoice().getPayed()) { // Check if the invoice is unpaid
                Client client = registration.getClient();
                if (!clientsWithUnpaidInvoices.contains(client)) { // Avoid duplicate entries
                    clientsWithUnpaidInvoices.add(client);
                }
            }
        }

        return clientsWithUnpaidInvoices;
    }
}

