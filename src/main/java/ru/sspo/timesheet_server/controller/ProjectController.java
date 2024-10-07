package ru.sspo.timesheet_server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sspo.timesheet_server.model.Project;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.rest.API;
import ru.sspo.timesheet_server.service.ProjectService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Tag(name = "Projects", description = "API для работы с проектами")
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @Operation(
            summary = "Get Project",
            description = "Получить проект по его идентификатору",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Project.class))
                    )
            }
    )
    @API.NotFoundResponse
    @API.ServerErrorResponse
    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
        Project project = projectService.getById(id)
                .orElseThrow(() -> new NoSuchElementException("Проект не найден"));
        return ResponseEntity.ok(project);
    }


    @Operation(
            summary = "Get All Projects",
            description = "Получить все проекты",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ",
                            responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Project.class))
                            )
                    )
            }
    )
    @API.ServerErrorResponse
    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(projectService.getAll());
    }

    @Operation(
            summary = "Create Project",
            description = "Создать проект",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = Project.class)
                            )
                    )
            }
    )
    @API.BadRequestResponse
    @API.ServerErrorResponse
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.create(project));
    }

    @Operation(
            summary = "Delete Project",
            description = "Удалить проект по его идентификатору",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ",
                            responseCode = "204",
                            content = @Content(schema = @Schema(implementation = Void.class)
                            )
                    )
            }
    )
    @API.NotFoundResponse
    @API.ServerErrorResponse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор проекта") Long id){
        projectService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get Project Timesheets",
            description = "Получить табели проекта по его идентификатору",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ",
                            responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Timesheet.class))
                            )
                    )
            }
    )
    @API.NotFoundResponse
    @API.ServerErrorResponse
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(
            @PathVariable @Parameter(description = "Идентификатор проекта") Long id){
        List<Timesheet> timesheets = projectService.getProjectTimesheets(id);
        return ResponseEntity.ok(timesheets);
    }
}
