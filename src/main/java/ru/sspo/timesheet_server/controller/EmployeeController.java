package ru.sspo.timesheet_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sspo.timesheet_server.model.Employee;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/{projectId}")
    public ResponseEntity<Employee> get(@PathVariable(name = "employeeId") Long id) {
        Optional<Employee> optionalEmployee = service.findById(id);

        return optionalEmployee.map(employee -> ResponseEntity.status(HttpStatus.OK).body(employee))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(employee));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "employeeId") Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{employeeId}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable(name = "employeeId") Long id){
        List<Timesheet> timesheets = service.getEmployeeTimesheets(id);
        return ResponseEntity.ok(timesheets);
    }
}
