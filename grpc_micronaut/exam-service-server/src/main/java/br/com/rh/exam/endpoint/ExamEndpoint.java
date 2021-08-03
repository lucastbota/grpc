package br.com.rh.exam.endpoint;

import br.com.rh.exam.endpoint.stream.AsoScheduleStream;
import br.com.rh.proto.AsoRequest;
import br.com.rh.proto.AsoResponse;
import br.com.rh.proto.service.AsoServiceGrpc;
import io.grpc.stub.StreamObserver;

import javax.inject.Singleton;

@Singleton
public class ExamEndpoint extends AsoServiceGrpc.AsoServiceImplBase {
    @Override
    public StreamObserver<AsoRequest> scheduleAso(StreamObserver<AsoResponse> responseObserver) {
        return new AsoScheduleStream(responseObserver);
    }
}
