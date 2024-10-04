package ru.sspo.timesheet_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sspo.timesheet_server.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
