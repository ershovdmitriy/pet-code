package org.example.petcode.controller;

import org.example.petcode.dto.response.TaskResponse;
import org.example.petcode.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "task-list";
    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable Long id, Model model) {
        TaskResponse task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task-pattern";
    }
}