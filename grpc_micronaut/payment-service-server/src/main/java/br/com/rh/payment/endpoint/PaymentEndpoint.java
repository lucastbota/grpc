package br.com.rh.payment.endpoint;

import br.com.rh.payment.service.PaymentService;
import br.com.rh.proto.PaymentRequest;
import br.com.rh.proto.PaymentResponse;
import br.com.rh.proto.PaymentStatus;
import br.com.rh.proto.service.PaymentServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PaymentEndpoint extends PaymentServiceGrpc.PaymentServiceImplBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentEndpoint.class);
    @Inject
    private PaymentService paymentService;

    @Override
    public void payEmployees(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        LOGGER.info("Requisition Received.");
        var ids = request.getEmployeeIdsList();

        ids.forEach(id -> {
            LOGGER.info("Processing: {}", id);
            var paid = paymentService.pay(id);
            LOGGER.info("Paid?: {}", paid);
            var response = PaymentResponse.newBuilder().setEmployeeId(id).setStatus(paid ? PaymentStatus.BANK_ENQUEUED : PaymentStatus.ALREADY_PAID).build();
            responseObserver.onNext(response);
        });
        LOGGER.info("Processing is done.");
        responseObserver.onCompleted();
    }
}
