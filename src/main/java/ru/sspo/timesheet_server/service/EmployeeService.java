package ru.sspo.timesheet_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sspo.timesheet_server.model.Employee;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.repository.EmployeeRepository;
import ru.sspo.timesheet_server.repository.TimesheetRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TimesheetRepository timesheetRepository;

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee create(Employee employee) {
        String name = employee.getName();

        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("Employee name must not be null or empty");
        }
        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        if (employeeRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Employee with id = " + id + " does not exist.");
        }
        employeeRepository.deleteById(id);
    }

    public List<Timesheet> getEmployeeTimesheets(Long id) {
        if (employeeRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Employee with id = " + id + " does not exist.");
        }
        return timesheetRepository.findByEmployeeId(id);
    }
}
