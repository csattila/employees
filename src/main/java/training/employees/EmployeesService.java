package training.employees;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeesService {

    private DataEmployeesRepository repository;
    private EventStoreGateway eventStoreGateway;
    private ModelMapper modelMapper;

    public EmployeesService(DataEmployeesRepository repository, EventStoreGateway eventStoreGateway, ModelMapper modelMapper) {
        this.repository = repository;
        this.eventStoreGateway = eventStoreGateway;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        var employee = repository.save(new Employee(command.getName()));
//        eventStoreGateway.sendEvent(new CreateEventCommand("New employee created: " + employee.getId() + " - " + employee.getName()));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<EmployeeDto> listEmployees(String prefix) {
        log.info("Alkalmazottak listazasa");
        log.debug("A parameter: {}", prefix);
//        return employeesRepository.listAllEmployees(prefix).stream()
//                .filter(e -> prefix == null || e.getName().toLowerCase().startsWith(prefix.toLowerCase()))
//                .map(e -> modelMapper.map(e, EmployeeDto.class))
//                .collect(Collectors.toList());
        return repository.findAll().stream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found")),
                EmployeeDto.class);
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        var employee = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        employee.setName(command.getName());
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        repository.deleteById(id);
    }

    public void deleteAll() { repository.deleteAll(); }
}
