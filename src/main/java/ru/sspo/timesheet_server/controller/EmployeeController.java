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
import ru.sspo.timesheet_server.model.Employee;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.rest.API;
import ru.sspo.timesheet_server.service.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Tag(name = "Employees", description = "API для работы с персоналом")
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(
            summary = "Get Employee",
            description = "Получить сотрудника по его идентификатору",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Employee.class))
                    )
            }
    )
    @API.NotFoundResponse
    @API.ServerErrorResponse
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(
            @PathVariable @Parameter(description = "Идентификатор записи сотрудника") Long id) {
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Запись сотрудника не найдена"));
        return ResponseEntity.ok(employee);
    }


    @Operation(
            summary = "Get All Employees",
            description = "Получить всех сотрудников",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ",
                            responseCode = "200",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Employee.class))
                            )
                    )
            }
    )
    @API.ServerErrorResponse
    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @Operation(
            summary = "Create Employee",
            description = "Создать запись сотрудника",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = Employee.class)
                            )
                    )
            }
    )
    @API.BadRequestResponse
    @API.ServerErrorResponse
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(employee));
    }

    @Operation(
            summary = "Delete Employee",
            description = "Удалить запись сотрудника по его идентификатору",
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
            @PathVariable @Parameter(description = "Идентификатор записи сотрудника") Long id) {
        employeeService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get Employee Timesheets",
            description = "Получить табели сотрудника по его идентификатору",
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
            @PathVariable @Parameter(description = "Идентификатор записи сотрудника") Long id) {
        List<Timesheet> timesheets = employeeService.getEmployeeTimesheets(id);
        return ResponseEntity.ok(timesheets);
    }
}
