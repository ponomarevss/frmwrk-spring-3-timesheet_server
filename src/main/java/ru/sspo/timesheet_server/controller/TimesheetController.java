package ru.sspo.timesheet_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sspo.timesheet_server.model.Timesheet;
import ru.sspo.timesheet_server.service.TimesheetService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timesheets") // here you have all resource duplications
public class TimesheetController {

    // GET - get something, does not contain body

//    @GetMapping("/timesheets")
//    public Timesheet get() {
//        Timesheet timesheet = new Timesheet();
//        timesheet.setId(1L);
//        timesheet.setProject("spring");
//        timesheet.setMinutes(200);
//        timesheet.setCreatedAt(LocalDate.now());
//
//        return timesheet;
//    }

//    @GetMapping("/timesheets/{id}") // get particular by id
//    @DeleteMapping("/timesheets/{id}") // delete particular by id
//    @PutMapping("/timesheets/{id}") // update particular by id

//    @GetMapping("/timesheets") // get all
//    public List<Timesheet> get() {
//        Timesheet timesheet = new Timesheet();
//        timesheet.setId(1L);
//        timesheet.setProject("spring");
//        timesheet.setMinutes(110);
//        timesheet.setCreatedAt(LocalDate.now());
//
//        Timesheet timesheet2 = new Timesheet();
//        timesheet2.setId(2L);
//        timesheet2.setProject("fall");
//        timesheet2.setMinutes(220);
//        timesheet2.setCreatedAt(LocalDate.now());
//
//        return List.of(timesheet, timesheet2);
//    }

    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> get(@PathVariable Long id) {
        Optional<Timesheet> ts = service.getById(id);

        if (ts.isPresent()) {
//            return ResponseEntity.ok(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }
        return ResponseEntity.notFound().header("ty che", "ya niche").build();
    }


    @GetMapping
    public ResponseEntity<List<Timesheet>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping // create new
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        timesheet = service.create(timesheet);

        // 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);

        // 204 No Content
        return ResponseEntity.noContent().build();
    }
}
