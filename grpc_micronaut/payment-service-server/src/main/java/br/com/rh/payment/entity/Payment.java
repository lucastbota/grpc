package br.com.rh.payment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "payment")
@IdClass(PaymentPK.class)
public class Payment implements Serializable {
    @Id
    @Column(name = "pay_month")
    private Integer month;
    @Id
    @Column(name = "pay_emp_id")
    private Long employeeId;

    public Payment() {
    }

    public Payment(PaymentPK id) {
        this.month = id.getMonth();
        this.employeeId = id.getEmployeeId();
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return getMonth().equals(payment.getMonth()) && getEmployeeId().equals(payment.getEmployeeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMonth(), getEmployeeId());
    }
}
