package br.com.rh.manager.service.impl;

import br.com.rh.manager.service.ExamService;
import br.com.rh.manager.stream.AsoStream;
import br.com.rh.proto.AsoRequest;
import br.com.rh.proto.AsoResponse;
import br.com.rh.proto.service.AsoServiceGrpc;
import io.grpc.stub.StreamObserver;
import io.micronaut.context.annotation.Prototype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Prototype
public class ExamRemoteService implements ExamService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExamRemoteService.class);
    private final AsoServiceGrpc.AsoServiceStub asoServiceStub;

    public ExamRemoteService(AsoServiceGrpc.AsoServiceStub asoServiceStub) {
        this.asoServiceStub = asoServiceStub;
    }

    @Override
    public void scheduleAsoExam(List<Long> employees) {
        StreamObserver<AsoResponse> responseStreamObserver = new AsoStream();
        StreamObserver<AsoRequest> requestStreamObserver = asoServiceStub.scheduleAso(responseStreamObserver);
        employees.forEach(employeeId -> {
            AsoRequest request = AsoRequest.newBuilder().setEmployeeId(employeeId).build();
            requestStreamObserver.onNext(request);
        });
        requestStreamObserver.onCompleted();
    }
}
