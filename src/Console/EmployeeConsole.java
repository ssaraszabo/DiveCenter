package Console;

import Domain.Employee;
import Controller.EmployeeController;

import java.util.Scanner;
import java.util.List;

public class EmployeeConsole {
    private EmployeeController employeeController;
    private Scanner scanner;

    public EmployeeConsole(EmployeeController employeeController) {
        this.employeeController = employeeController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Employee Management System:");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. View All Employees");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    viewAllEmployees();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addEmployee() {
        System.out.print("Enter employee name: ");
        scanner.nextLine();  // Consume newline
        String name = scanner.nextLine();
        System.out.print("Enter employee age: ");
        int age = scanner.nextInt();
        System.out.print("Enter employee contact info: ");
        scanner.nextLine();  // Consume newline
        String contactInfo = scanner.nextLine();
        System.out.print("Enter employee position: ");
        String position = scanner.nextLine();
        System.out.print("Enter employee employment date (YYYY-MM-DD): ");
        String employmentDate = scanner.nextLine();

        Employee employee = new Employee(0, name, age, contactInfo, position, employmentDate);
        employeeController.createEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private void viewEmployee() {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        Employee employee = employeeController.getEmployeeById(id);
        if (employee != null) {
            System.out.println("Employee details: " + employee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void updateEmployee() {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();

        Employee existingEmployee = employeeController.getEmployeeById(id);
        if (existingEmployee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("Updating employee details. Leave blank to keep current value.");

        System.out.print("Enter new name (current: " + existingEmployee.getName() + "): ");     //update name
        scanner.nextLine();  //consume newline
        String name = scanner.nextLine();
        if (!name.isBlank()) {
            existingEmployee.setName(name);
        }

        System.out.print("Enter new age (current: " + existingEmployee.getAge() + "): ");       //update age
        String ageInput = scanner.nextLine();
        if (!ageInput.isBlank()) {
            try {
                int age = Integer.parseInt(ageInput);
                existingEmployee.setAge(age);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input. Keeping current age.");
            }
        }

        System.out.print("Enter new contact info (current: " + existingEmployee.getContactInfo() + "): ");  //update contact info
        String contactInfo = scanner.nextLine();
        if (!contactInfo.isBlank()) {
            existingEmployee.setContactInfo(contactInfo);
        }

        System.out.print("Enter new position (current: " + existingEmployee.getPosition() + "): ");
        String position = scanner.nextLine();
        if (!position.isBlank()) {
            existingEmployee.setPosition(position);
        }

        System.out.print("Enter new employment date (current: " + existingEmployee.getEmploymentDate() + "): ");
        String employmentDate = scanner.nextLine();
        if (!employmentDate.isBlank()) {
            existingEmployee.setEmploymentDate(employmentDate);
        }

        employeeController.updateEmployee(existingEmployee);        //pass to controller
        System.out.println("Employee updated successfully.");
    }

    private void deleteEmployee() {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        employeeController.deleteEmployee(id);
        System.out.println("Employee deleted successfully.");
    }

    private void viewAllEmployees() {
        List<Employee> employees = employeeController.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            employees.forEach(System.out::println);
        }
    }
}
