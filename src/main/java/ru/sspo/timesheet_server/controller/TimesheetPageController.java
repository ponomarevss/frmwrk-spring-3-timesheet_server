package ru.sspo.timesheet_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sspo.timesheet_server.service.TimesheetPageService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/timesheets")
@RequiredArgsConstructor
public class TimesheetPageController {

    private final TimesheetPageService timesheetPageService;

    @GetMapping
    public String getAllTimesheetsPage(Model model) {
        List<TimesheetPageDto> timesheets = timesheetPageService.findAll();
        model.addAttribute("timesheets", timesheets);
        return "timesheets-page.html";
    }

    @GetMapping("/{id}")
    public String getTimesheetsPage(@PathVariable Long id, Model model) {
        Optional<TimesheetPageDto> optionalTimesheetPageDto = timesheetPageService.findById(id);
        if (optionalTimesheetPageDto.isEmpty()) {
            return "not-found.html";
        }

        model.addAttribute("timesheet", optionalTimesheetPageDto.get());
        return "timesheet-page.html";
    }
}
