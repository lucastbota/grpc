package br.com.rh.manager.service;

import br.com.rh.proto.EmployeeRequest;

import java.util.Map;

public interface EmployeeService {

    void hire(EmployeeRequest request);
    void changeUo(Map<Long, Long> employeeUos);
}
