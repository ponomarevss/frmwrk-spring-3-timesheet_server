package ru.sspo.timesheet_server.service;

import org.springframework.stereotype.Service;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.repository.ProjectRepository;
import ru.sspo.timesheet_server.repository.TimesheetRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository timesheetRepository, ProjectRepository projectRepository) {
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
    }

    public Optional<Timesheet> getById(Long id) {
        return timesheetRepository.getById(id);
    }

    public List<Timesheet> getAll() {
        return timesheetRepository.getAll();
    }

    public Timesheet create(Timesheet timesheet) {
        if (Objects.isNull(timesheet.getProjectId())) {
            throw new IllegalArgumentException("Project must not be null");
        }
        if (projectRepository.getById(timesheet.getProjectId()).isEmpty()) {
            throw new NoSuchElementException("Project with id " + timesheet.getProjectId() + " does not exist");
        }
        timesheet.setCreatedAt(LocalDateTime.now());
        return timesheetRepository.create(timesheet);
    }

    public void delete(Long id){
        timesheetRepository.delete(id);
    }
}
