package ru.sspo.timesheet_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.repository.EmployeeRepository;
import ru.sspo.timesheet_server.repository.ProjectRepository;
import ru.sspo.timesheet_server.repository.TimesheetRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public Optional<Timesheet> findById(Long id) {
        return timesheetRepository.findById(id);
    }

    public List<Timesheet> findAll() {
        return timesheetRepository.findAll();
    }

    public Timesheet create(Timesheet timesheet) {
        Long projectId = timesheet.getProjectId();
        Long employeeId = timesheet.getEmployeeId();

        if (Objects.isNull(projectId)) {
            throw new IllegalArgumentException("Project id must not be null");
        }
        if (Objects.isNull(employeeId)) {
            throw new IllegalArgumentException("Employee id must not be null");
        }
        if (projectRepository.findById(projectId).isEmpty()) {
            throw new NoSuchElementException("Project with id " + projectId + " does not exist");
        }
        if (employeeRepository.findById(employeeId).isEmpty()) {
            throw new NoSuchElementException("Employee with id " + employeeId + " does not exist");
        }

        timesheet.setCreatedAt(LocalDateTime.now());
        return timesheetRepository.save(timesheet);
    }

    public void delete(Long id){
        if (timesheetRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Timesheet with id " + id + " does not exist");
        }
        timesheetRepository.deleteById(id);
    }
}
