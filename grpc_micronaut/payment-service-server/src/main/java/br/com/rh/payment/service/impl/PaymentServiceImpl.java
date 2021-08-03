package br.com.rh.payment.service.impl;

import br.com.rh.payment.entity.Payment;
import br.com.rh.payment.entity.PaymentPK;
import br.com.rh.payment.repository.PaymentRepository;
import br.com.rh.payment.service.PaymentService;
import io.micronaut.context.annotation.Prototype;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Prototype
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public boolean pay(Long id) {
        var currentMonth = LocalDate.now().getMonth().getValue();
        var paymentId = new PaymentPK(currentMonth, id);
        var paid = false;
        var paymentExists = repository.findById(paymentId) != null;

        if (!paymentExists) {
            repository.save(new Payment(paymentId));
            paid = true;
        }

        return paid;
    }
}
