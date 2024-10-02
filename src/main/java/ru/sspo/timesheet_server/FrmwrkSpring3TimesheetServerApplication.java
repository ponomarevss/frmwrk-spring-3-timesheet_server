package ru.sspo.timesheet_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.sspo.timesheet_server.model.Project;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.repository.ProjectRepository;
import ru.sspo.timesheet_server.repository.TimesheetRepository;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class FrmwrkSpring3TimesheetServerApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FrmwrkSpring3TimesheetServerApplication.class, args);

		// generate projects
		ProjectRepository projectRepository = context.getBean(ProjectRepository.class);
		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setName("Project #" + i);
			projectRepository.create(project);
		}

		// generate timesheets
		TimesheetRepository timesheetRepository = context.getBean(TimesheetRepository.class);

		LocalDateTime createdAt = LocalDateTime.now();
		for (int i = 0; i < 10; i++) {
			createdAt = createdAt.plusDays(1);

			Timesheet timesheet = new Timesheet();
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

			timesheetRepository.create(timesheet); 
		}
	}
}