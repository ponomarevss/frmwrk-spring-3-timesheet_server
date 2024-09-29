package ru.sspo.timesheet_server.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.repository.TimesheetRepository;

import java.util.List;
import java.util.Optional;

@Service // same as @Component
public class TimesheetService {

    private final TimesheetRepository repository;

    public TimesheetService(TimesheetRepository repository) {
        this.repository = repository;
    }

    public Optional<Timesheet> getById(Long id) {
        return repository.getById(id);
    }


    public List<Timesheet> getAll() {
        return repository.getAll();
    }

    public Timesheet create(Timesheet timesheet) {
        return repository.create(timesheet);
    }

    public void delete(Long id){
        repository.delete(id);
    }

}
