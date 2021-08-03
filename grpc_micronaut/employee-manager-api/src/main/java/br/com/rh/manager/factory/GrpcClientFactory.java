package br.com.rh.manager.factory;

import br.com.rh.proto.service.AsoServiceGrpc;
import br.com.rh.proto.service.EmployeeServiceGrpc;
import br.com.rh.proto.service.PaymentServiceGrpc;
import io.grpc.ManagedChannel;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;

import javax.inject.Singleton;

@Factory
public class GrpcClientFactory {
    @Singleton
    public EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub(
            @GrpcChannel("employee-service-server") ManagedChannel channel) {
        return EmployeeServiceGrpc.newBlockingStub(channel);
    }

    @Singleton
    public EmployeeServiceGrpc.EmployeeServiceStub employeeServiceStub(
            @GrpcChannel("employee-service-server") ManagedChannel channel) {
        return EmployeeServiceGrpc.newStub(channel);
    }

    @Singleton
    public PaymentServiceGrpc.PaymentServiceBlockingStub paymentServiceBlockingStub(
            @GrpcChannel("payment-service-server") ManagedChannel channel) {
        return PaymentServiceGrpc.newBlockingStub(channel);
    }

    @Singleton
    public AsoServiceGrpc.AsoServiceStub asoServiceStub(@GrpcChannel("aso-service-server") ManagedChannel channel) {
        return AsoServiceGrpc.newStub(channel);
    }
}