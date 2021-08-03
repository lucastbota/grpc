package br.com.rh.data;

import br.com.rh.dto.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {
    private static final Logger LOGGER = LoggerFactory.getLogger(Reader.class);

    public List<EmployeeDTO> fillFrom(String file) {
        List<EmployeeDTO> empList = Collections.emptyList();
        try (var br = new BufferedReader(new FileReader(file))) {
            Stream<String> lines = br.lines().skip(1);
            empList = lines.map(l -> l.split(",")).map(toDtoFunction()).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            LOGGER.error("file not found.", e);
        } catch (IOException e) {
            LOGGER.error("IO Error.", e);
        }
        return empList;
    }

    private Function<String[], EmployeeDTO> toDtoFunction() {
        return fields -> {
            var dto = new EmployeeDTO();
            dto.setId(Long.valueOf(fields[0]));
            dto.setName(fields[1]);
            dto.setSalary(new BigDecimal(fields[2]));
            dto.setEmail(fields[3]);
            dto.setUoId(Long.valueOf(fields[4]));
            return dto;
        };
    }
}