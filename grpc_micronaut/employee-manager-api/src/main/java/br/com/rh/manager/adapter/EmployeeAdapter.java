package br.com.rh.manager.adapter;

import br.com.rh.dto.EmployeeDTO;
import br.com.rh.proto.EmployeeRequest;


public class EmployeeAdapter {
    public static EmployeeRequest toEmployeeRequest(EmployeeDTO dto) {
        var builder = EmployeeRequest.newBuilder();
        if (dto != null) {
            if (dto.getId() != null)
                builder.setId(dto.getId());
            if (dto.getSalary() != null)
                builder.setSalary(dto.getSalary().doubleValue());
            builder.setEmail(dto.getEmail()).setName(dto.getName())
                    .setUoId(dto.getUoId());
        }
        return builder.build();
    }
}
