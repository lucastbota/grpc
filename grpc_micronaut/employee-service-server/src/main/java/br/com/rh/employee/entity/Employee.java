package br.com.rh.employee.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import static br.com.rh.employee.entity.Employee.QUERY_BY_ALL;
import static br.com.rh.employee.entity.Employee.QUERY_BY_ALL_KEY;

@Entity
@Table(name = "employee")
@NamedQuery(name = QUERY_BY_ALL_KEY, query = QUERY_BY_ALL)
public class Employee implements Serializable {
    protected static final String QUERY_BY_ALL = "SELECT e FROM Employee e ORDER BY e.id DESC";
    public static final String QUERY_BY_ALL_KEY = "queryByAll";

    @Id
    @Column(name = "emp_id")
    private Long id;
    @Column(name = "emp_name", length = 300)
    private String name;
    @Column(name = "emp_salary")
    private BigDecimal salary;
    @Column(name = "emp_email")
    private String email;
    @Column(name = "uo_id")
    private Long organizationUnitId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getOrganizationUnitId() {
        return organizationUnitId;
    }

    public void setOrganizationUnitId(Long organizationUnitId) {
        this.organizationUnitId = organizationUnitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getId().equals(employee.getId()) && getName().equals(employee.getName()) && getEmail().equals(employee.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", organizationUnitId=" + organizationUnitId +
                '}';
    }
}
