package ru.sspo.timesheet_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sspo.timesheet_server.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
