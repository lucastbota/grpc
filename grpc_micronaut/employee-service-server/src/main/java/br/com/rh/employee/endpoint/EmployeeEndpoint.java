package br.com.rh.employee.endpoint;

import br.com.rh.employee.endpoint.stream.UoStream;
import br.com.rh.employee.entity.Employee;
import br.com.rh.employee.service.EmployeeService;
import br.com.rh.proto.ChangeUoRequest;
import br.com.rh.proto.ChangeUoResponse;
import br.com.rh.proto.EmployeeRequest;
import br.com.rh.proto.EmployeeResponse;
import br.com.rh.proto.service.EmployeeServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.math.BigDecimal;

@Singleton
public class EmployeeEndpoint extends EmployeeServiceGrpc.EmployeeServiceImplBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeEndpoint.class);
    private final EmployeeService employeeService;

    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Unary
    public void hire(EmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {
        if (!request.getAllFields().isEmpty()) {
            Employee employee = new Employee();

            if (0L != request.getId())
                employee.setId(request.getId());
            employee.setName(request.getName());
            employee.setEmail(request.getEmail());
            employee.setSalary(BigDecimal.valueOf(request.getSalary()));
            employee.setOrganizationUnitId(request.getUoId());
            employeeService.save(employee);

            var response = EmployeeResponse.newBuilder().setProtocolNumber(System.currentTimeMillis()).build();
            responseObserver.onNext(response);
        } else {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Payload vazio!").asException());
        }
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<ChangeUoRequest> changeUnitOrganization(StreamObserver<ChangeUoResponse> responseObserver) {
        return new UoStream(responseObserver, employeeService);
    }
}