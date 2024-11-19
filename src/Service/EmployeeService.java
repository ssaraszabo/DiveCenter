package Service;

import Domain.Employee;
import Repository.FileRepository;
import Repository.IRepository;

import java.util.List;

public class EmployeeService {
    private IRepository<Employee> employeeRepository;

    public EmployeeService(IRepository<Employee> employeeRepository) {

        //this.employeeRepository = employeeRepository;
        /**
         * Initializes a new instance of EmployeeService with a FileRepository.
         */
        this.employeeRepository = new FileRepository<>(
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
    }

    public void addEmployee(Employee employee) {
        employeeRepository.create(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.read(id);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.update(employee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.delete(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.readAll();
    }
}
