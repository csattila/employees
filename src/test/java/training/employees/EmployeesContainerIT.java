package training.employees;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesContainerIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    EmployeesService employeesService;

    @BeforeEach
    void clearEmployees(){
        employeesService.deleteAll();
    }

    @Test
    void testCreateEmployee(){
        template.postForObject("/api/employees",
                new CreateEmployeeCommand("Szilva"),
                EmployeeDto.class);

//        List<EmployeeDto> employees = template.getForObject("/api/employees", List.class);
        List<EmployeeDto> employees = template.exchange("/api/employees",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EmployeeDto>>() {}).getBody();

        System.out.println(employees);

        Assertions.assertThat(employees)
                .extracting(EmployeeDto::getName)
                .containsExactly("Szilva");
    }
}
