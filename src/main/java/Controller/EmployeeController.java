package Controller;

import Domain.Employee;
import Service.EmployeeService;

import java.util.List;

public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void createEmployee(Employee employee) {
        employeeService.addEmployee(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeeService.getEmployeeById(id);
    }

    public void updateEmployee(Employee employee) {
        employeeService.updateEmployee(employee);
    }

    public void deleteEmployee(int id) {
        employeeService.deleteEmployee(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
