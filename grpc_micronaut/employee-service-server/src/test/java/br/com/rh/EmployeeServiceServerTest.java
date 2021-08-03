package br.com.rh;

import br.com.rh.proto.EmployeeRequest;
import br.com.rh.proto.service.EmployeeServiceGrpc;
import io.grpc.StatusRuntimeException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
class EmployeeServiceServerTest {

    @Inject
    private EmployeeServiceGrpc.EmployeeServiceBlockingStub serviceBlockingStub;

    @Test
    void testItWorks() {
        Assertions.assertThrows(StatusRuntimeException.class, () -> serviceBlockingStub.hire(EmployeeRequest.newBuilder().build()));
    }

}
