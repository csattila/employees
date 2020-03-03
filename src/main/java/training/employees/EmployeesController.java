package training.employees;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    public EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService){
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<EmployeeDto> listEmployees(@RequestParam(required = false) String prefix){
        return employeesService.listEmployees(prefix);
    }

    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable("id") long id){
        return employeesService.findEmployeeById(id);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity findEmployeeById(@PathVariable("id") long id){
//        try {
//            return ResponseEntity.ok(employeesService.findEmployeeById(id));
//        } catch (IllegalArgumentException e){
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@Valid @RequestBody CreateEmployeeCommand command){
        return employeesService.createEmployee(command);
    }

    @PostMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeCommand command){
        return employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") long id)
//            throws EmployeeNotFoundException
    {
        employeesService.deleteEmployee(id);
        return ResponseEntity.noContent().build();

    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleNotFound(){
        System.out.println("Employee not found");
    }
}
