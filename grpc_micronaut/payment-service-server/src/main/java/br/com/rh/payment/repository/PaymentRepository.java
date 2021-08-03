package br.com.rh.payment.repository;

import br.com.rh.payment.entity.Payment;
import br.com.rh.payment.entity.PaymentPK;

public interface PaymentRepository {
    void save(Payment payment);
    Payment findById(PaymentPK id);
}
