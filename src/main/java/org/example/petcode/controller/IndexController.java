package org.example.petcode.controller;

import org.example.petcode.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final TaskService taskService;

    @GetMapping("/")
    public String redirectToTasks() {
        return "redirect:/tasks";
    }
}