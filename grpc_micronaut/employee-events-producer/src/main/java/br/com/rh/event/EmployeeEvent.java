package br.com.rh.event;

import br.com.rh.dto.EmployeeDTO;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import java.util.List;
import java.util.Map;

@KafkaClient
public interface EmployeeEvent {
    @Topic("employee-management-hire")
    void sendHireEvent(@KafkaKey long key, EmployeeDTO dto);

    @Topic("employee-change-uo")
    void sendChangeUoEvent(@KafkaKey long key, Map<Long, Long> employeeUos);

    @Topic("employee-payment")
    void sendPaymentEvent(@KafkaKey long key, List<Long> employees);

    @Topic("employee-aso-schedule")
    void sendAsoScheduleEvent(@KafkaKey long key, List<Long> employees);
}
