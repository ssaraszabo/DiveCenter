package Service;

import Domain.Employee;
import Repository.IRepository;

import java.util.List;

public class EmployeeService {
    private IRepository<Employee> employeeRepository;

    public EmployeeService(IRepository<Employee> employeeRepository) {
        this.employeeRepository = employeeRepository;
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
