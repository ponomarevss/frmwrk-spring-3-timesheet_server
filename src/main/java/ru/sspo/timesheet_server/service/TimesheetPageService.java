package ru.sspo.timesheet_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sspo.timesheet_server.controller.TimesheetPageDto;
import ru.sspo.timesheet_server.model.Project;
import ru.sspo.timesheet_server.model.Timesheet;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {

    private final TimesheetService timesheetService;
    private final ProjectService projectService;

    public List<TimesheetPageDto> findAll() {
        return timesheetService.findAll().stream().map(this::convert).toList();
    }

    public Optional<TimesheetPageDto> findById(Long id) {
        return timesheetService.findById(id).map(this::convert);
    }

    private TimesheetPageDto convert(Timesheet timesheet) {
        Project project = projectService.getById(timesheet.getProjectId()).orElseThrow();

        TimesheetPageDto timesheetPageDto = new TimesheetPageDto();

        timesheetPageDto.setProjectId(String.valueOf(project.getId()));
        timesheetPageDto.setProjectName(project.getName());
        timesheetPageDto.setId(String.valueOf(timesheet.getId()));
        timesheetPageDto.setMinutes(String.valueOf(timesheet.getMinutes()));
        timesheetPageDto.setCreatedAt(timesheet.getCreatedAt().toString());

        return timesheetPageDto;
    }
}
