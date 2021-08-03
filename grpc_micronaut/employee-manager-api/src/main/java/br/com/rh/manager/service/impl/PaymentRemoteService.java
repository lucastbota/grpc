package br.com.rh.manager.service.impl;

import br.com.rh.manager.service.PaymentService;
import br.com.rh.proto.PaymentRequest;
import br.com.rh.proto.service.PaymentServiceGrpc;
import io.micronaut.context.annotation.Prototype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Prototype
public class PaymentRemoteService implements PaymentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentRemoteService.class);
    private final PaymentServiceGrpc.PaymentServiceBlockingStub paymentServiceBlockingStub;

    public PaymentRemoteService(PaymentServiceGrpc.PaymentServiceBlockingStub paymentServiceBlockingStub) {
        this.paymentServiceBlockingStub = paymentServiceBlockingStub;
    }

    @Override
    public void payEmployees(List<Long> employees) {
        var paymentResponse = paymentServiceBlockingStub.payEmployees(PaymentRequest.newBuilder().addAllEmployeeIds(employees).build());
        while (paymentResponse.hasNext()) {
            var res = paymentResponse.next();
            LOGGER.info("response: {}-{}", res.getEmployeeId(), res.getStatus());
        }
    }
}