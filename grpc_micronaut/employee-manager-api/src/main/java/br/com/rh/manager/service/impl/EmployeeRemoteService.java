package br.com.rh.manager.service.impl;

import br.com.rh.manager.service.EmployeeService;
import br.com.rh.manager.stream.ChangeUoStream;
import br.com.rh.proto.ChangeUoRequest;
import br.com.rh.proto.EmployeeRequest;
import br.com.rh.proto.service.EmployeeServiceGrpc;
import io.grpc.stub.StreamObserver;
import io.micronaut.context.annotation.Prototype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Prototype
public class EmployeeRemoteService implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRemoteService.class);
    private final EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub;
    private final EmployeeServiceGrpc.EmployeeServiceStub employeeServiceStub;

    public EmployeeRemoteService(EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub, EmployeeServiceGrpc.EmployeeServiceStub employeeServiceStub) {
        this.employeeServiceBlockingStub = employeeServiceBlockingStub;
        this.employeeServiceStub = employeeServiceStub;
    }

    @Override
    public void hire(EmployeeRequest request) {
        var response = employeeServiceBlockingStub.hire(request);
        LOGGER.info("Protocol: {}", response.getProtocolNumber());
    }

    @Override
    public void changeUo(Map<Long, Long> employeeUos) {
        StreamObserver<ChangeUoRequest> requestStream = employeeServiceStub.changeUnitOrganization(new ChangeUoStream());

        employeeUos.forEach((k,v) -> {
            var request = ChangeUoRequest.newBuilder().setEmployeeId(k).setUoId(v).build();
            requestStream.onNext(request);
        });
        requestStream.onCompleted();
    }
}
