package br.com.rh.service;

import br.com.rh.dto.EmployeeDTO;
import br.com.rh.event.EmployeeEvent;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Singleton
public class EmployeeEventService implements EmployeeService {
    @Inject
    private EmployeeEvent employeeEvent;

    @Override
    public void hire(EmployeeDTO employeeDTO) {
        employeeEvent.sendHireEvent(Instant.now().toEpochMilli(), employeeDTO);
    }

    @Override
    public void changeUo(Map<Long, Long> employeeUos) {
        employeeEvent.sendChangeUoEvent(Instant.now().toEpochMilli(), employeeUos);
    }

    @Override
    public void payEmployees(List<Long> employees) {
        employeeEvent.sendPaymentEvent(Instant.now().toEpochMilli(), employees);
    }

    @Override
    public void scheduleAso(List<Long> employees) {
        employeeEvent.sendAsoScheduleEvent(Instant.now().toEpochMilli(), employees);
    }
}
