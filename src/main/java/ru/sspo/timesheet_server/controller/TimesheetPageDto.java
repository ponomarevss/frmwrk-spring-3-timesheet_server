package ru.sspo.timesheet_server.controller;

import lombok.Data;

@Data
public class TimesheetPageDto {

        private String projectId;
        private String projectName;
        private String id;
        private String minutes;
        private String createdAt;
}
