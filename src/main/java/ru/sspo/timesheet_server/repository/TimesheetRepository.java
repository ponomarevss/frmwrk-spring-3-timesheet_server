package ru.sspo.timesheet_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sspo.timesheet_server.model.Timesheet;

import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findByProjectId(Long projectId);
    List<Timesheet> findByEmployeeId(Long employeeId);
}
