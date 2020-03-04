package training.employees;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.*;

@WebMvcTest //csak a controller reteg tesztelese
public class EmployeesControllerIT {

    @MockBean
    EmployeesService employeesService;

    @MockBean
    HelloService helloService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testCreate() throws Exception {
        Mockito.when(employeesService.createEmployee(any())).thenReturn(new EmployeeDto(1L, "Alma"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Alma\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(r -> System.out.println("XXXXXXXXXXXXXXX: " + r.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.equalTo("Alma")))
                ;

        Mockito.verify(employeesService).createEmployee(argThat(e -> e.getName().equals("Alma")));
        Mockito.verify(employeesService, Mockito.never()).listEmployees(anyString());
    }
}
