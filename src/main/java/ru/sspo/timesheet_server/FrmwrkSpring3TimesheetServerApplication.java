package ru.sspo.timesheet_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.sspo.timesheet_server.model.Employee;
import ru.sspo.timesheet_server.model.Project;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.service.EmployeeService;
import ru.sspo.timesheet_server.service.ProjectService;
import ru.sspo.timesheet_server.service.TimesheetService;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class FrmwrkSpring3TimesheetServerApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FrmwrkSpring3TimesheetServerApplication.class, args);

//        // generate employees
//        EmployeeService employeeService = context.getBean(EmployeeService.class);
//        List<String> employeeNames = List.of("Emma Carter", "Jacob Thompson", "Mia Rodriguez", "Oliver Bennett", "Ava Patel");
//        List<String> employeeEmails = List.of("emma.carter@email.com", "jacob.thompson@email.com", "mia.rodriguez@email.com", "oliver.bennett@email.com", "ava.patel@email.com");
//        for (int i = 0; i < employeeNames.size(); i++) {
//            Employee employee = new Employee();
//            employee.setName(employeeNames.get(i));
//            employee.setEmail(employeeEmails.get(i));
//            employeeService.create(employee);
//        }
//        List<Employee> employees = employeeService.getAll();
//
//        // generate projects
//        ProjectService projectService = context.getBean(ProjectService.class);
//        List<String> projectNames = List.of("Quantum Leap", "Echo Sphere", "Urban Pulse", "Nebula Forge", "Prism Shift");
//        for (int i = 0; i < projectNames.size(); i++) {
//            Project project = new Project();
//            project.setName(projectNames.get(i));
//            projectService.create(project);
//        }
//        List<Project> projects = projectService.getAll();
//
//        // generate timesheets
//        TimesheetService timesheetService = context.getBean(TimesheetService.class);
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            Timesheet timesheet = new Timesheet();
//            timesheet.setProjectId(projects.get(random.nextInt(projects.size())).getId());
//            timesheet.setEmployeeId(employees.get(random.nextInt(employees.size())).getId());
//            timesheet.setMinutes(random.nextInt(60, 480));
//            timesheetService.create(timesheet);
//        }
    }
}