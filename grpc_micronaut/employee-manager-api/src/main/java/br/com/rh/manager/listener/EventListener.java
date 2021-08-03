package br.com.rh.manager.listener;

import br.com.rh.dto.EmployeeDTO;
import br.com.rh.manager.facade.EmployeeFacade;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;


@KafkaListener
public class EventListener {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(EventListener.class);
    @Inject
    private EmployeeFacade facade;

    @Topic("employee-management-hire")
    public void receiveHireEvent(@KafkaKey Long key, EmployeeDTO employeeDTO) {
        LOGGER.info("Event received: {}-{}", key, employeeDTO);
        facade.hire(employeeDTO);
    }


    @Topic("employee-change-uo")
    void receiveChangeUoEvent(@KafkaKey long key, Map<Long, Long> employeeUos) {
        LOGGER.info("Change UO Event received: {}, {}", key, employeeUos);
        facade.changeUo(employeeUos);
    }

    @Topic("employee-payment")
    void receivePaymentEvent(@KafkaKey long key, List<Long> employees) {
        LOGGER.info("Payment Event received: {}, {}", key, employees);
        facade.payEmployees(employees);
    }

    @Topic("employee-aso-schedule")
    void receiveAsoScheduleEvent(@KafkaKey long key, List<Long> employees) {
        LOGGER.info("Schedule Event received: {}, {}", key, employees);
        facade.scheduleAsoExam(employees);
    }
}
