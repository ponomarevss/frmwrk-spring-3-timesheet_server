package ru.sspo.timesheet_server.service;

import org.springframework.stereotype.Service;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.repository.ProjectRepository;
import ru.sspo.timesheet_server.repository.TimesheetRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TimesheetService {

    private final TimesheetRepository repository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository repository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

    public Optional<Timesheet> getById(Long id) {
        return repository.getById(id);
    }

    public List<Timesheet> getAll() {
        return repository.getAll();
    }

    public Timesheet create(Timesheet timesheet) {
        if (projectRepository.getById(timesheet.getProjectId()).isPresent()) {
            timesheet.setCreatedAt(LocalDateTime.now());
            return repository.create(timesheet);
        }
        return null;
    }

    public void delete(Long id){
        repository.delete(id);
    }
}
