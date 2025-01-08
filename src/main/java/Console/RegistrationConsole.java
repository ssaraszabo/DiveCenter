package Console;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import Controller.RegistrationController;
import Controller.ClientController;
import Controller.CourseController;
import Domain.Client;
import Domain.Course;
import Domain.Employee;
import Domain.Registration;

public class RegistrationConsole {
    private RegistrationController registrationController;
    private ClientController clientController;
    private CourseController courseController;
    private Scanner scanner;

    public RegistrationConsole(RegistrationController registrationController, ClientController clientController, CourseController courseController) {
        this.registrationController = registrationController;
        this.clientController = clientController;
        this.courseController = courseController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Registration Management System:");
            System.out.println("1. Add Registration");
            System.out.println("2. View Registration");
            System.out.println("3. Update Registration Time");
            System.out.println("4. Delete Registration");
            System.out.println("5. View All Registrations");
            System.out.println("6. View Clients with Unpaid Invoices");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addRegistration();
                    break;
                case 2:
                    viewRegistration();
                    break;
                case 3:
                    updateRegistrationTime();
                    break;
                case 4:
                    deleteRegistration();
                    break;
                case 5:
                    viewAllRegistrations();
                    break;
                case 6:
                    viewClientsWithUnpaidInvoices();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addRegistration() {
        System.out.print("Enter registration ID: ");
        int registrationID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Retrieve specific client
        System.out.print("Enter client ID: ");
        int clientID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Client client = clientController.getClient(clientID);  // Fetching client via ClientController
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        // Retrieve specific course
        System.out.print("Enter course ID: ");
        int courseID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Course course = courseController.getCourse(courseID);  // Fetching course via CourseController
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter registration amount: ");
        int amount = scanner.nextInt();

        Registration registration = registrationController.addRegistration(registrationID, client, course, amount);
        System.out.println("Registration added successfully. Details: " + registration);
    }

    private void viewRegistration() {
        System.out.print("Enter registration ID: ");
        int registrationID = scanner.nextInt();
        Registration registration = registrationController.getRegistration(registrationID);

        if (registration != null) {
            System.out.println("Registration details: " + registration);
        } else {
            System.out.println("Registration not found.");
        }
    }


    private void updateRegistrationTime() {
        System.out.print("Enter registration ID to update: ");
        int registrationID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Registration existingRegistration = registrationController.getRegistration(registrationID);
        if (existingRegistration == null) {
            System.out.println("Registration not found.");
            return;
        }

        System.out.print("Enter new registration time (YYYY-MM-DD HH:mm): ");
        String newTimeStr = scanner.nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date newTime = dateFormat.parse(newTimeStr);
            registrationController.modifyRegistrationTime(registrationID, newTime);
            System.out.println("Registration time updated successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD HH:mm format.");
        }
    }

    private void deleteRegistration() {
        System.out.print("Enter registration ID to delete: ");
        int registrationID = scanner.nextInt();
        registrationController.deleteRegistration(registrationID);
        System.out.println("Registration deleted successfully.");
    }

    private void viewAllRegistrations() {
        List<Registration> registrations = registrationController.listRegistrations();
        if (registrations.isEmpty()) {
            System.out.println("No registrations found.");
        } else {
            registrations.forEach(System.out::println);
        }
    }

    private void viewClientsWithUnpaidInvoices() {
        System.out.println("Clients with unpaid invoices:");
        List<Client> clients = registrationController.getClientsWithUnpaidInvoices();
        if (clients.isEmpty()) {
            System.out.println("No clients with unpaid invoices.");
        } else {
            clients.forEach(System.out::println);
        }
    }
}

