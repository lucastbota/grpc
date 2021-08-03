package br.com.rh.payment.repository.jpa;

import br.com.rh.payment.entity.Payment;
import br.com.rh.payment.entity.PaymentPK;
import br.com.rh.payment.repository.PaymentRepository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;

@Singleton
public class PaymentJpaRepository implements PaymentRepository {

    private final EntityManager entityManager;

    public PaymentJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Payment payment) {
        this.entityManager.persist(payment);
    }

    @Override
    public Payment findById(PaymentPK id) {
        return this.entityManager.find(Payment.class, id);
    }
}
