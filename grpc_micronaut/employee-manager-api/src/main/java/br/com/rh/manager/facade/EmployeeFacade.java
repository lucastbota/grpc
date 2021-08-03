package br.com.rh.manager.facade;

import br.com.rh.dto.EmployeeDTO;

import java.util.List;
import java.util.Map;

public interface EmployeeFacade {
    void hire(EmployeeDTO employeeDTO);

    void changeUo(Map<Long, Long> employeeUos);

    void payEmployees(List<Long> employees);

    void scheduleAsoExam(List<Long> employees);
}
