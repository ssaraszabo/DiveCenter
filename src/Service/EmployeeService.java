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

    /**
     * Adds a new employee to the repository.
     *
     * @param employee The employee to add.
     */
    public void addEmployee(Employee employee) {
        employeeRepository.create(employee);
    }

    /**
     * Retrieves an employee by id.
     *
     * @param id ID of employee to be retrieved.
     * @return Employee with the specified id.
     */
    public Employee getEmployeeById(int id) {
        return employeeRepository.read(id);
    }

    /**
     * Updates the details of an employee.
     *
     * @param employee Employee that is to be updated.
     */
    public void updateEmployee(Employee employee) {
        employeeRepository.update(employee);
    }

    /**
     * Deletes an employee.
     *
     * @param id Id of the employee to be deleted.
     */
    public void deleteEmployee(int id) {
        employeeRepository.delete(id);
    }

    /**
     * Retrieves all employees.
     *
     * @return List of all employees.
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.readAll();
    }
}
