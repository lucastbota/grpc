package br.com.rh.employee.repository.jpa;

import br.com.rh.employee.entity.Employee;
import br.com.rh.employee.repository.EmployeeRepository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Singleton
public class EmployeeJpaRepository implements EmployeeRepository {
    private final EntityManager entityManager;

    public EmployeeJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getEmployees() {
        TypedQuery<Employee> employeeTypedQuery = entityManager.createNamedQuery(Employee.QUERY_BY_ALL_KEY, Employee.class);
        return employeeTypedQuery.getResultList();
    }

    @Override
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(System.currentTimeMillis());
            entityManager.persist(employee);
        } else {
            entityManager.merge(employee);
        }
    }

    @Override
    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }
}
