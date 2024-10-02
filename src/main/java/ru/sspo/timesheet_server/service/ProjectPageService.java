package ru.sspo.timesheet_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sspo.timesheet_server.controller.ProjectPageDto;
import ru.sspo.timesheet_server.model.Project;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectPageService {

    private final ProjectService projectService;

    public Optional<ProjectPageDto> findById(Long id) {
        return projectService.getById(id).map(this::convert);
    }

    private ProjectPageDto convert(Project project) {
        ProjectPageDto projectPageDto = new ProjectPageDto();

        projectPageDto.setId(String.valueOf(project.getId()));
        projectPageDto.setName(project.getName());

        return projectPageDto;
    }
}
