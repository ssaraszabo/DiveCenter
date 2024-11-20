
import Console.ClientConsole;
import Console.CourseConsole;
import Console.EquipmentConsole;
import Console.InvoiceConsole;
import Console.MembershipConsole;
import Console.PaymentConsole;
import Console.ScheduleConsole;

import Controller.*;

import Domain.*;

import Repository.FileRepository;
import Repository.IRepository;
import Service.*;

import java.util.Date;
import java.util.Scanner;
import Console.RegistrationConsole;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        InMemoryRepository<Client> clientRepository = new InMemoryRepository<>(Client::getId);
//        InMemoryRepository<Equipment> equipmentRepository = new InMemoryRepository<>(Equipment::getEquipmentID);
//        InMemoryRepository<Invoice> invoiceRepository = new InMemoryRepository<>(Invoice::getInvoiceId);
//        InMemoryRepository<Membership> membershipRepository = new InMemoryRepository<>(Membership::getMembershipID);
//        InMemoryRepository<Payment> paymentRepository = new InMemoryRepository<>(Payment::getPaymentID);
//        InMemoryRepository<Schedule> scheduleRepository = new InMemoryRepository<>(Schedule::getScheduleID);
//        InMemoryRepository<Registration> registrationRepository = new InMemoryRepository<>(Registration::getRegistrationID);

        IRepository<Employee> employeeRepository = new FileRepository<>(
                "employees.txt",
                Employee::getId,
                line -> {
                    String[] parts = line.split(",");
                    return new Employee(
                            Integer.parseInt(parts[0]), //id
                            parts[1],                   //name
                            Integer.parseInt(parts[2]), //age
                            parts[3],                   //contactInfo
                            parts[4],                   //position
                            parts[5]                    //employmentDate
                    );
                },
                employee -> String.join(",",
                        String.valueOf(employee.getId()),
                        employee.getName(),
                        String.valueOf(employee.getAge()),
                        employee.getContactInfo(),
                        employee.getPosition(),
                        employee.getEmploymentDate()
                )
        );
        IRepository<Client> clientRepository = new FileRepository<>(
                "clients.txt",
                Client::getId,
                line -> {
                    String[] parts = line.split(",");
                    return new Client(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            Integer.parseInt(parts[2]),
                            parts[3],
                            parts[4],
                            Boolean.parseBoolean(parts[5])
                    );
                },
                client -> String.join(",",
                        String.valueOf(client.getId()),
                        client.getName(),
                        String.valueOf(client.getAge()),
                        client.getContactInfo(),
                        client.getexperienceLevel(),
                        String.valueOf(client.isMember())
                )
        );
        IRepository<Course> courseRepository = new FileRepository<>(
                "courses.txt",
                Course::getCourseID,
                line -> {
                    String[] parts = line.split(",");
                    return new Course(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            new Date(Long.parseLong(parts[2])),
                            Integer.parseInt(parts[3]),
                            parts[4],
                            Integer.parseInt(parts[5]),
                            Integer.parseInt(parts[6])
                    );
                },
                course -> String.join(",",
                        String.valueOf(course.getCourseID()),
                        course.getName(),
                        String.valueOf(course.getStartTime().getTime()),
                        String.valueOf(course.getMinAge()),
                        course.getExperienceRequired(),
                        String.valueOf(course.getMaxCapacity()),
                        String.valueOf(course.getCurrentCapacity())
                )
        );
        IRepository<Equipment> equipmentRepository = new FileRepository<>(
                "equipment.txt",
                Equipment::getEquipmentID,
                line -> {
                    String[] parts = line.split(",");
                    return new Equipment(
                            Integer.parseInt(parts[0]), //equipmentID
                            parts[1],                   //type
                            Integer.parseInt(parts[2]), //condition
                            new Date(Long.parseLong(parts[3])) //lastMaintenanceDate(as timestamp)
                    );
                },
                equipment -> String.join(",",
                        String.valueOf(equipment.getEquipmentID()),
                        equipment.getType(),
                        String.valueOf(equipment.getCondition()),
                        String.valueOf(equipment.getLastmaintainancedate().getTime())
                )
        );
        IRepository<Invoice> invoiceRepository = new FileRepository<>(
                "invoices.txt",
                Invoice::getInvoiceId,
                line -> {
                    String[] parts = line.split(",");
                    return new Invoice(
                            Integer.parseInt(parts[0]),         //invoiceId
                            Integer.parseInt(parts[1]),         //amount
                            Boolean.parseBoolean(parts[3]),     //payment
                            new Date(Long.parseLong(parts[2])) //issueDate(as timestamp)
                    );
                    },
                invoice -> String.join(",",
                        String.valueOf(invoice.getInvoiceId()),
                        String.valueOf(invoice.getAmount()),
                        String.valueOf(invoice.getPayed()),
                        String.valueOf(invoice.getIssueDate().getTime())
                )
        );
        IRepository<Membership> membershipRepository = new FileRepository<>(
                "memberships.txt",
                Membership::getMembershipID,
                line -> {
                    String[] parts = line.split(",");
                    return new Membership(
                            Integer.parseInt(parts[0]),         //membershipID
                            new Date(Long.parseLong(parts[1])), //startDate (as timestamp)
                            new Date(Long.parseLong(parts[2])), //endDate (as timestamp)
                            parts[3]                            //membershipType
                    );
                },
                membership -> String.join(",",
                        String.valueOf(membership.getMembershipID()),
                        String.valueOf(membership.getStartDate().getTime()),
                        String.valueOf(membership.getEndDate().getTime()),
                        membership.getMembershipType()
                )
        );
        IRepository<Payment> paymentRepository = new FileRepository<>(
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
        IRepository<Registration> registrationRepository = new FileRepository<>(
                "registrations.txt",
                registration -> registration.getRegistrationID(),
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
        IRepository<Schedule> scheduleRepository = new FileRepository<>(
                "schedules.txt",
                Schedule::getScheduleID,
                line -> {
                    String[] parts = line.split(",");
                    return new Schedule(
                            Integer.parseInt(parts[0]),         //scheduleID
                            Integer.parseInt(parts[1]),         //employeeID
                            new Date(Long.parseLong(parts[2])), //startTime
                            new Date(Long.parseLong(parts[3])) //endTime
                    );
                },
                schedule -> String.join(",",
                        String.valueOf(schedule.getScheduleID()),
                        String.valueOf(schedule.getEmployeeID()),
                        String.valueOf(schedule.getStartTime().getTime()),
                        String.valueOf(schedule.getEndTime().getTime())
                )
        );

        EmployeeService employeeService = new EmployeeService(employeeRepository);
        EmployeeController employeeController = new EmployeeController(employeeService);

        ClientService clientService = new ClientService(clientRepository);
        ClientController clientController = new ClientController(clientService);

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

        RegistrationService registrationService = new RegistrationService(registrationRepository);
        RegistrationController registrationController = new RegistrationController(registrationService);

        ClientConsole clientConsole = new ClientConsole(clientController);
        CourseConsole courseConsole = new CourseConsole(courseController);
        EquipmentConsole equipmentConsole = new EquipmentConsole(equipmentController);
        InvoiceConsole invoiceConsole = new InvoiceConsole(invoiceController);
        MembershipConsole membershipConsole = new MembershipConsole(membershipController);
        PaymentConsole paymentConsole = new PaymentConsole(paymentController);
        ScheduleConsole scheduleConsole = new ScheduleConsole(scheduleController);
        RegistrationConsole registrationConsole = new RegistrationConsole(registrationController,clientController,courseController);

        while (true) {
            System.out.println("Main Menu");
            System.out.println("1. Manage Clients");
            System.out.println("2. Manage Courses");
            System.out.println("3. Manage Equipment");
            System.out.println("4. Manage Invoices");
            System.out.println("5. Manage Memberships");
            System.out.println("6. Manage Payments");
            System.out.println("7. Manage Schedules");
            System.out.println("8. Manage Registrations");
            System.out.println("9. Exit");
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
                    registrationConsole.displayMenu();
                    return;
                case 9:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}