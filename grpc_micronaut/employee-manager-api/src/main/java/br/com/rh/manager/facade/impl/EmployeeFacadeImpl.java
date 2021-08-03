package br.com.rh.manager.facade.impl;

import br.com.rh.dto.EmployeeDTO;
import br.com.rh.manager.adapter.EmployeeAdapter;
import br.com.rh.manager.facade.EmployeeFacade;
import br.com.rh.manager.service.EmployeeService;
import br.com.rh.manager.service.ExamService;
import br.com.rh.manager.service.PaymentService;
import io.micronaut.context.annotation.Prototype;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Prototype
public class EmployeeFacadeImpl implements EmployeeFacade {
    @Inject
    private EmployeeService employeeService;
    @Inject
    private PaymentService paymentService;
    @Inject
    private ExamService examService;

    @Override
    public void hire(EmployeeDTO employeeDTO) {
        this.employeeService.hire(EmployeeAdapter.toEmployeeRequest(employeeDTO));
    }

    @Override
    public void changeUo(Map<Long, Long> employeeUos) {
        this.employeeService.changeUo(employeeUos);
    }

    @Override
    public void payEmployees(List<Long> employees) {
        this.paymentService.payEmployees(employees);
    }

    @Override
    public void scheduleAsoExam(List<Long> employees) {
        this.examService.scheduleAsoExam(employees);
    }
}
