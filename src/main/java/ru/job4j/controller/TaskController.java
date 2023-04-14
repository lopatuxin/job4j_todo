package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Task;
import ru.job4j.model.User;
import ru.job4j.services.CategoryService;
import ru.job4j.services.PriorityService;
import ru.job4j.services.TaskService;

import static ru.job4j.util.TimezoneConverter.setTimeZone;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService service;
    private final PriorityService priorityService;
    private final CategoryService categoryService;

    @GetMapping()
    public String getAll(Model model, @SessionAttribute User user) {
        var tasks = service.findAll();
        tasks.forEach(task -> setTimeZone(task, user));
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @GetMapping("/false")
    public String getNew(Model model, @SessionAttribute User user) {
        var tasks = service.findByDone(false);
        tasks.forEach(task -> setTimeZone(task, user));
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @GetMapping("/true")
    public String getSuccess(Model model, @SessionAttribute User user) {
        var tasks = service.findByDone(true);
        tasks.forEach(task -> setTimeZone(task, user));
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @GetMapping({"/create", "tasks/create"})
    public String getCreationPage(Model model) {
        model.addAttribute("priorities", priorityService.findAll());
        model.addAttribute("categories", categoryService.findAll());
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
    public String getById(Model model, @PathVariable int id, @SessionAttribute User user) {
        var taskOptional = service.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Заявка с указанным идентификатором не найдена");
            return "errors/404";
        }
        setTimeZone(taskOptional.get(), user);
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
