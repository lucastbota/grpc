package br.com.rh.employee.service;

import br.com.rh.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();
    void save(Employee employee);
    void changeUo(Long employeeId, Long uoId);
}
