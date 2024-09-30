package ru.sspo.timesheet_server.service;

import org.springframework.stereotype.Service;
import ru.sspo.timesheet_server.model.Project;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.repository.ProjectRepository;
import ru.sspo.timesheet_server.repository.TimesheetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TimesheetRepository timesheetRepository;

    public ProjectService(ProjectRepository projectRepository, TimesheetRepository timesheetRepository) {
        this.projectRepository = projectRepository;
        this.timesheetRepository = timesheetRepository;
    }

    public Optional<Project> getById(Long id) {
        return projectRepository.getById(id);
    }

    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    public Project create(Project project) {
        return projectRepository.create(project);
    }

    public void delete(Long id){
        projectRepository.delete(id);
    }

    public List<Timesheet> getProjectTimesheets(Long id) {
        if (projectRepository.getById(id).isEmpty()) {
            return null;
        }
        return timesheetRepository.getByProjectId(id);
    }
}
