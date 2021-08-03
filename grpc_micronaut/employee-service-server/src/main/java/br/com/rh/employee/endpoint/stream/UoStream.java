package br.com.rh.employee.endpoint.stream;

import br.com.rh.employee.service.EmployeeService;
import br.com.rh.proto.ChangeUoRequest;
import br.com.rh.proto.ChangeUoResponse;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UoStream implements StreamObserver<ChangeUoRequest> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UoStream.class);
    private StreamObserver<ChangeUoResponse> responseObserver;
    private final EmployeeService service;

    public UoStream(StreamObserver<ChangeUoResponse> responseObserver, EmployeeService service) {
        this.responseObserver = responseObserver;
        this.service = service;
    }

    @Override
    public void onNext(ChangeUoRequest value) {
        LOGGER.info("Changing....");
        service.changeUo(value.getEmployeeId(), value.getUoId());
        LOGGER.info("Changed....");
    }


    @Override
    public void onError(Throwable t) {
        LOGGER.error("Error while processing requisitions", t);
    }

    @Override
    public void onCompleted() {
        LOGGER.info("Finished. Generating protocol...");
        var protocol = System.currentTimeMillis();
        LOGGER.info("Protocol created: {}", protocol);

        responseObserver.onNext(ChangeUoResponse.newBuilder().setProtocolNumber(protocol).build());
        responseObserver.onCompleted();
    }
}
