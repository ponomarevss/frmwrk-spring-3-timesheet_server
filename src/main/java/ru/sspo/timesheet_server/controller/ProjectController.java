package ru.sspo.timesheet_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sspo.timesheet_server.model.Project;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.service.ProjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> get(@PathVariable(name = "projectId") Long id) {
        Optional<Project> optionalProject = service.getById(id);

        return optionalProject.map(project -> ResponseEntity.status(HttpStatus.OK).body(project))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        project = service.create(project);

        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "projectId") Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{projectId}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable(name = "projectId") Long id){
        List<Timesheet> timesheets = service.getProjectTimesheets(id);
        return ResponseEntity.ok(timesheets);
    }
}
