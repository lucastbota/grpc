package br.com.rh;

import br.com.rh.data.Reader;
import br.com.rh.dto.EmployeeDTO;
import br.com.rh.service.EmployeeService;
import io.micronaut.context.ApplicationContext;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        var data = new Reader().fillFrom("data.csv");
        try (ApplicationContext context = ApplicationContext.run(args)) {
            EmployeeService service = context.getBean(EmployeeService.class);
            //sendHireEvent(data, service);
            //sendChangeUoEvent(data, service);
            //sendPaymentEvent(data, service);
            sendAsoScheduleEvent(data, service);
        }
    }

    private static void sendHireEvent(List<EmployeeDTO> data, EmployeeService service) {
        var firstEmp = data.get(0);
        firstEmp.setId(null);
        service.hire(firstEmp);
    }

    private static void sendChangeUoEvent(List<EmployeeDTO> data, EmployeeService service) {
        var employeeUos = data.stream().skip(1).limit(200).collect(Collectors.toMap(EmployeeDTO::getId, e -> e.getUoId() * 3));
        service.changeUo(employeeUos);
    }

    private static void sendPaymentEvent(List<EmployeeDTO> data, EmployeeService service) {
        var employees = data.stream().map(EmployeeDTO::getId).filter(Objects::nonNull).collect(Collectors.toList());

        service.payEmployees(employees);
        //evento que gerará already paid
        service.payEmployees(employees);
    }


    private static void sendAsoScheduleEvent(List<EmployeeDTO> data, EmployeeService service) {
        var employees = data.stream().map(EmployeeDTO::getId).filter(Objects::nonNull).limit(150).collect(Collectors.toList());
        service.scheduleAso(employees);
    }
}
