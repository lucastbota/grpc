package br.com.rh.employee.service.impl;

import br.com.rh.employee.entity.Employee;
import br.com.rh.employee.repository.EmployeeRepository;
import br.com.rh.employee.service.EmployeeService;
import io.micronaut.context.annotation.Prototype;

import javax.transaction.Transactional;
import java.util.List;

@Prototype
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getEmployees() {
        return repository.getEmployees();
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        repository.save(employee);
    }

    @Override
    @Transactional
    public void changeUo(Long employeeId, Long uoId) {
        Employee employee = repository.findById(employeeId);

        if (employee != null && !employee.getOrganizationUnitId().equals(uoId)) {
            employee.setOrganizationUnitId(uoId);
            repository.save(employee);
        }
    }
}
