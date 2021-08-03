package br.com.rh.employee.repository;

import br.com.rh.employee.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getEmployees();

    void save(Employee employee);

    Employee findById(Long id );
}
