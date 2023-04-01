package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.services.TaskService;

@Controller
@AllArgsConstructor
public class TaskController {
    private final TaskService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tasks", service.findAll());
        return "tasks/list";
    }

}
