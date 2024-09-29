package ru.sspo.timesheet_server.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class Timesheet {

    private Long id;
    private String project;
    private int minutes;
    private LocalDate createdAt;
}
