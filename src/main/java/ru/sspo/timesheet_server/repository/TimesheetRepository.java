package ru.sspo.timesheet_server.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import ru.sspo.timesheet_server.model.Timesheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository // @Component for classes that work with data
public class TimesheetRepository {

    private static Long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<>();

    public Optional<Timesheet> getById(Long id) {
        // select * from timesheets where id = $id
        return timesheets.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
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
