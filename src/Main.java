
import Console.ClientConsole;
import Console.CourseConsole;
import Console.EquipmentConsole;
import Console.InvoiceConsole;
import Console.MembershipConsole;
import Console.PaymentConsole;
import Console.ScheduleConsole;

import Controller.*;

import Domain.*;

import Repository.DBRepository;
import Repository.IRepository;
import Service.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import Console.RegistrationConsole;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Connection string for the database
        String connectionString = "jdbc:sqlserver://localhost;databaseName=DiveCenterMAP;user=(usernameTODO);password=(passwordTODO)";

        // Initialize DBRepository for Client
        IRepository<Client> clientRepository = new DBRepository<>(
                connectionString,
                "Clients",
                resultSet -> {
                    try {
                        return new Client(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("contactInfo"),
                                resultSet.getString("experienceLevel"),
                                resultSet.getBoolean("isMember")
                        );
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                },
                client -> new Object[]{
                        client.getId(),
                        client.getName(),
                        client.getAge(),
                        client.getContactInfo(),
                        client.getexperienceLevel(),
                        client.isMember()
                },
                Client::getId
        );
        // Initialize DBRepository for Course
        IRepository<Course> courseRepository = new DBRepository<>(
                connectionString,
                "Courses",
                resultSet -> {
                    try {
                        return new Course(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                new java.util.Date(resultSet.getTimestamp("startTime").getTime()),
                                resultSet.getInt("minAge"),
                                resultSet.getString("experienceRequired"),
                                resultSet.getInt("maxCapacity"),
                                resultSet.getInt("currentCapacity")
                        );
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                },
                course -> new Object[]{
                        course.getCourseID(),
                        course.getName(),
                        new java.sql.Timestamp(course.getStartTime().getTime()),
                        course.getMinAge(),
                        course.getExperienceRequired(),
                        course.getMaxCapacity(),
                        course.getCurrentCapacity()
                },
                Course::getCourseID
        );
        // Initialize DBRepository for Employee
        IRepository<Employee> employeeRepository = new DBRepository<>(
                connectionString,
                "Employees",
                resultSet -> {
                    try {
                        return new Employee(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getString("contactInfo"),
                                resultSet.getString("position"),
                                resultSet.getString("employmentDate")
                        );
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                },
                employee -> new Object[]{
                        employee.getId(),
                        employee.getName(),
                        employee.getAge(),
                        employee.getContactInfo(),
                        employee.getPosition(),
                        employee.getEmploymentDate()
                },
                Employee::getId
        );
        // Initialize DBRepository for Equipment
        IRepository<Equipment> equipmentRepository = new DBRepository<>(
                connectionString,
                "Equipment",
                resultSet -> {
                    try {
                        return new Equipment(
                                resultSet.getInt("id"),
                                resultSet.getString("type"),
                                resultSet.getInt("condition"),
                                new java.util.Date(resultSet.getTimestamp("lastMaintenanceDate").getTime())
                        );
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                },
                equipment -> new Object[]{
                        equipment.getEquipmentID(),
                        equipment.getType(),
                        equipment.getCondition(),
                        new java.sql.Timestamp(equipment.getLastmaintainancedate().getTime())
                },
                Equipment::getEquipmentID
        );
        //Initialize DBRepository for Invoice
        IRepository<Invoice> invoiceRepository = new DBRepository<>(
                connectionString,
                "Invoices",
                resultSet -> {
                    try {
                        return new Invoice(
                                resultSet.getInt("id"),
                                resultSet.getInt("amount"),
                                new java.util.Date(resultSet.getTimestamp("issueDate").getTime())
                        );
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                },
                invoice -> new Object[]{
                        invoice.getInvoiceId(),
                        invoice.getAmount(),
                        new java.sql.Timestamp(invoice.getIssueDate().getTime())
                },
                Invoice::getInvoiceId
        );
        //Initialize DBRepository for Membership
        IRepository<Membership> membershipRepository = new DBRepository<>(
                connectionString,
                "Memberships",
                resultSet -> {
                    try {
                        return new Membership(
                                resultSet.getInt("id"),
                                new java.util.Date(resultSet.getTimestamp("startDate").getTime()),
                                new java.util.Date(resultSet.getTimestamp("endDate").getTime()),
                                resultSet.getString("membershipType")
                        );
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                },
                membership -> new Object[]{
                        membership.getMembershipID(),
                        new java.sql.Timestamp(membership.getStartDate().getTime()),
                        new java.sql.Timestamp(membership.getEndDate().getTime()),
                        membership.getMembershipType()
                },
                Membership::getMembershipID
        );
        //Initialize DBRepository for Payment
        IRepository<Payment> paymentRepository = new DBRepository<>(
                connectionString,
                "Payments",
                resultSet -> {
                    try {
                        return new Payment(
                                resultSet.getInt("id"),
                                resultSet.getInt("membershipID"),
                                resultSet.getDouble("amount"),
                                new java.util.Date(resultSet.getTimestamp("paymentDate").getTime())
                        );
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                },
                payment -> new Object[]{
                        payment.getPaymentID(),
                        payment.getMembershipID(),
                        payment.getAmount(),
                        new java.sql.Timestamp(payment.getPaymentDate().getTime())
                },
                Payment::getPaymentID
        );
        //Iniatialize DBRepository for Schedule
        IRepository<Schedule> scheduleRepository = new DBRepository<>(
                connectionString,
                "Schedules",
                resultSet -> {
                    try {
                        return new Schedule(
                                resultSet.getInt("id"),
                                resultSet.getInt("employeeID"),
                                new java.util.Date(resultSet.getTimestamp("startTime").getTime()),
                                new java.util.Date(resultSet.getTimestamp("endTime").getTime())
                        );
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                },
                schedule -> new Object[]{
                        schedule.getScheduleID(),
                        schedule.getEmployeeID(),
                        new java.sql.Timestamp(schedule.getStartTime().getTime()),
                        new java.sql.Timestamp(schedule.getEndTime().getTime())
                },
                Schedule::getScheduleID
        );
        //Initialize DBRepository for Registration
        IRepository<Registration> registrationRepository = new DBRepository<>(
                connectionString,
                "Registrations",
                resultSet -> {
                    try {
                        return new Registration(
                                resultSet.getInt("id"),
                                new java.util.Date(resultSet.getTimestamp("registrationDate").getTime()),
                                resultSet.getString("status"),
                                null, // Assume associations (Client, Course, Invoice) are lazy-loaded
                                null,
                                null
                        );
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }
                },
                registration -> new Object[]{
                        registration.getRegistrationID(),
                        new java.sql.Timestamp(registration.getRegistrationDate().getTime()),
                        registration.getStatus()
                },
                Registration::getRegistrationID
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

        RegistrationService registrationService = new RegistrationService(registrationRepository,invoiceController);
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