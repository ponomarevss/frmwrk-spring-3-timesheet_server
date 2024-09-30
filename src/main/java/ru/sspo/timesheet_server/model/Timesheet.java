package ru.sspo.timesheet_server.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Timesheet {

    private Long id;
    private Long projectId;
    private int minutes;
    private LocalDateTime createdAt;
}
