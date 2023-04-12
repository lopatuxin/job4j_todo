package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Task;
import ru.job4j.model.User;
import ru.job4j.services.PriorityService;
import ru.job4j.services.TaskService;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService service;
    private final PriorityService priorityService;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("tasks", service.findAll());
        return "tasks/list";
    }

    @GetMapping("/false")
    public String getNew(Model model) {
        model.addAttribute("tasks", service.findByDone(false));
        return "tasks/list";
    }

    @GetMapping("/true")
    public String getSuccess(Model model) {
        model.addAttribute("tasks", service.findByDone(true));
        return "tasks/list";
    }

    @GetMapping({"/create", "tasks/create"})
    public String getCreationPage() {
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, Model model, @SessionAttribute User user) {
        task.setUser(user);
        var result = service.add(task);
        if (result == null) {
            model.addAttribute("message", "Не удалось добавить заявку");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var taskOptional = service.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Заявка с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/one";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        boolean result = service.delete(id);
        if (!result) {
            model.addAttribute("message", "Не удалось удалить заявку");
            return "errors/404";
        }
        return "redirect:/tasks";
    }
    @GetMapping("/updateDone/{id}")
    public String updateDone(@PathVariable int id, Model model) {
        boolean result = service.updateDone(id);
        if (!result) {
            model.addAttribute("message", "Заявка не выполнена");
            return "errors/404";
        }
        return "redirect:/tasks";
    }
    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable int id, Model model) {
        var taskOptional = service.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Заявка с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model) {
        boolean result = service.update(task);
        if (!result) {
            model.addAttribute("message", "Не удалось обновить заявку");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

}
