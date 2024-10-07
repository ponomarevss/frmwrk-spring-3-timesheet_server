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
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.rest.API;
import ru.sspo.timesheet_server.rest.ExceptionResponse;
import ru.sspo.timesheet_server.service.TimesheetService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Tag(name = "Timesheets", description = "API для работы с табелями")
@RequiredArgsConstructor
@RequestMapping("/timesheets")
public class TimesheetController {

    private final TimesheetService timesheetService;

    @Operation(
            summary = "Get Timesheet",
            description = "Получить табель по его идентификатору",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Timesheet.class))
                    )
            }
    )
    @API.NotFoundResponse
    @API.ServerErrorResponse
    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> getById(
            @PathVariable @Parameter(description = "Идентификатор табеля") Long id) {
        Timesheet timesheet = timesheetService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Табель не найден"));
        return ResponseEntity.ok(timesheet);
    }

    @Operation(
            summary = "Get All Timesheets",
            description = "Получить все табели",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ",
                            responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Timesheet.class))
                            )
                    )
            }
    )
    @API.ServerErrorResponse
    @GetMapping
    public ResponseEntity<List<Timesheet>> getAll() {
        return ResponseEntity.ok(timesheetService.findAll());
    }

    @Operation(
            summary = "Create Timesheet",
            description = "Создать табель",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = Timesheet.class)
                            )
                    )
            }
    )
    @API.BadRequestResponse
    @API.NotFoundResponse
    @API.ServerErrorResponse
    @PostMapping
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheetService.create(timesheet));
    }

    @Operation(
            summary = "Delete Timesheet",
            description = "Удалить табель по его идентификатору",
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
    public ResponseEntity<Void> delete(
            @PathVariable @Parameter(description = "Идентификатор табеля") Long id) {
        timesheetService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
