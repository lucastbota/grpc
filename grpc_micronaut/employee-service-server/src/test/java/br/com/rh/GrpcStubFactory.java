package br.com.rh;

import br.com.rh.proto.service.EmployeeServiceGrpc;
import io.grpc.ManagedChannel;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;
import io.micronaut.grpc.server.GrpcServerChannel;

@Factory
public class GrpcStubFactory {
    @Bean
    public EmployeeServiceGrpc.EmployeeServiceBlockingStub createBlockingStub(
            @GrpcChannel(GrpcServerChannel.NAME)
                    ManagedChannel channel) {
        return EmployeeServiceGrpc.newBlockingStub(channel);
    }
}
