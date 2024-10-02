package ru.sspo.timesheet_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sspo.timesheet_server.service.ProjectPageService;

import java.util.Optional;

@Controller
@RequestMapping("/home/projects")
@RequiredArgsConstructor
public class ProjectPageController {

    private final ProjectPageService projectPageService;

    @GetMapping("/{id}")
    public String getProjectPage(@PathVariable Long id, Model model) {
        Optional<ProjectPageDto> optionalProjectPageDto = projectPageService.findById(id);
        if (optionalProjectPageDto.isEmpty()) {
            return "not-found.html";
        }

        model.addAttribute("project", optionalProjectPageDto.get());
        return "project-page.html";
    }
}
