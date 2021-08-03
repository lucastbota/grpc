package br.com.rh.service;

import br.com.rh.dto.EmployeeDTO;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    void hire(EmployeeDTO employeeDTO);

    void changeUo(Map<Long, Long> employeeUos);

    void payEmployees(List<Long> employees);

    void scheduleAso(List<Long> employees);
}
