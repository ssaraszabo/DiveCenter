
import Console.ClientConsole;
import Console.CourseConsole;
import Console.EquipmentConsole;
import Console.InvoiceConsole;
import Console.MembershipConsole;
import Console.PaymentConsole;
import Console.ScheduleConsole;

import Controller.ClientController;
import Controller.CourseController;
import Controller.EquipmentController;
import Controller.InvoiceController;
import Controller.MembershipController;
import Controller.PaymentController;
import Controller.ScheduleController;

import Domain.*;
import Repository.CourseRepository;

import Repository.InMemoryRepository;
import Service.ClientService;
import Service.CourseService;
import Service.EquipmentService;
import Service.InvoiceService;
import Service.MembershipService;
import Service.PaymentService;
import Service.ScheduleService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        InMemoryRepository<Client> clientRepository = new InMemoryRepository<>(Client::getId);
        InMemoryRepository<Equipment> equipmentRepository = new InMemoryRepository<>(Equipment::getEquipmentID);
        InMemoryRepository<Invoice> invoiceRepository = new InMemoryRepository<>(Invoice::getInvoiceId);
        InMemoryRepository<Membership> membershipRepository = new InMemoryRepository<>(Membership::getMembershipID);
        InMemoryRepository<Payment> paymentRepository = new InMemoryRepository<>(Payment::getPaymentID);
        InMemoryRepository<Schedule> scheduleRepository = new InMemoryRepository<>(Schedule::getScheduleID);

        ClientService clientService = new ClientService(clientRepository);
        ClientController clientController = new ClientController(clientService);

        CourseRepository courseRepository = new CourseRepository();
        CourseService courseService = new CourseService(courseRepository);
        CourseController courseController = new CourseController(courseService);

        EquipmentService equipmentService = new EquipmentService(equipmentRepository);
        EquipmentController equipmentController = new EquipmentController(equipmentService);

        InvoiceService invoiceService = new InvoiceService(invoiceRepository);
        InvoiceController invoiceController = new InvoiceController(invoiceService);

        MembershipService membershipService = new MembershipService(membershipRepository);
        MembershipController membershipController = new MembershipController(membershipService);

        PaymentService paymentService = new PaymentService(paymentRepository);
        PaymentController paymentController = new PaymentController(paymentService);

        ScheduleService scheduleService = new ScheduleService(scheduleRepository);
        ScheduleController scheduleController = new ScheduleController(scheduleService);

        ClientConsole clientConsole = new ClientConsole(clientController);
        CourseConsole courseConsole = new CourseConsole(courseController);
        EquipmentConsole equipmentConsole = new EquipmentConsole(equipmentController);
        InvoiceConsole invoiceConsole = new InvoiceConsole(invoiceController);
        MembershipConsole membershipConsole = new MembershipConsole(membershipController);
        PaymentConsole paymentConsole = new PaymentConsole(paymentController);
        ScheduleConsole scheduleConsole = new ScheduleConsole(scheduleController);

        while (true) {
            System.out.println("Main Menu");
            System.out.println("1. Manage Clients");
            System.out.println("2. Manage Courses");
            System.out.println("3. Manage Equipment");
            System.out.println("4. Manage Invoices");
            System.out.println("5. Manage Memberships");
            System.out.println("6. Manage Payments");
            System.out.println("7. Manage Schedules");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    clientConsole.showMenu();
                    break;
                case 2:
                    courseConsole.showMenu();
                    break;
                case 3:
                    equipmentConsole.showMenu();
                    break;
                case 4:
                    invoiceConsole.showMenu();
                    break;
                case 5:
                    membershipConsole.showMenu();
                    break;
                case 6:
                    paymentConsole.showMenu();
                    break;
                case 7:
                    scheduleConsole.showMenu();
                    break;
                case 8:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}