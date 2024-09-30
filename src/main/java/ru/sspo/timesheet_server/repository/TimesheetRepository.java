package ru.sspo.timesheet_server.repository;

import org.springframework.stereotype.Repository;
import ru.sspo.timesheet_server.model.Timesheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TimesheetRepository {

    private static Long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<>();

    public Optional<Timesheet> getById(Long id) {
        return timesheets.stream()
                .filter(timesheet -> timesheet.getId().equals(id))
                .findFirst();
    }

    public List<Timesheet> getByProjectId(Long projectId) {
        return timesheets.stream()
                .filter(timesheet -> timesheet.getProjectId().equals(projectId))
                .collect(Collectors.toList());
    }


    public List<Timesheet> getAll() {
        return List.copyOf(timesheets);
    }

    public Timesheet create(Timesheet timesheet) {
        timesheet.setId(sequence++);
        timesheets.add(timesheet);
        return timesheet;
    }

    public void delete(Long id){
        timesheets.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresent(timesheets::remove);
    }
}
