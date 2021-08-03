package br.com.rh.payment.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PaymentPK implements Serializable {
    private Integer month;
    private Long employeeId;

    public PaymentPK(Integer month, Long employeeId) {
        this.month = month;
        this.employeeId = employeeId;
    }

    public PaymentPK() {
    }

    public Integer getMonth() {
        return month;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentPK paymentPK = (PaymentPK) o;
        return month.equals(paymentPK.month) && employeeId.equals(paymentPK.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, employeeId);
    }
}
