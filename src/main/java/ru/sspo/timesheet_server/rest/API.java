package ru.sspo.timesheet_server.rest;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class API {

    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            description = "Неверно составленный запрос",
            responseCode = "400",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
    )
    public @interface BadRequestResponse {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            description = "Ресурс не найден",
            responseCode = "404",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
    )
    public @interface NotFoundResponse {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(
            description = "Внутренняя ошибка",
            responseCode = "500",
            content = @Content(schema = @Schema(implementation = Void.class))
    )
    public @interface ServerErrorResponse {

    }
}
