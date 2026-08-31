package org.example.petcode.controller;

import org.example.petcode.dto.response.TaskResponse;
import org.example.petcode.entity.Submission;
import org.example.petcode.entity.Task;
import org.example.petcode.entity.User;
import org.example.petcode.service.SubmissionService;
import org.example.petcode.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.example.petcode.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final SubmissionService submissionService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "task-list";
    }

    @GetMapping("/{id}")
    public String showTask(@PathVariable Long id, Model model) {
        TaskResponse task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("testCases", taskService.getPublicTestCases(id));
        return "task-pattern";
    }

    @PostMapping("/{id}/solve")
    public String solveTask(@PathVariable Long id,
                            @RequestParam String code,
                            Principal principal,
                            Model model) {
        User user = userService.findByLogin(principal.getName());
        Task task = taskService.findTaskById(id);
        Submission submission = submissionService.createSubmission(user, task, code);
        return "redirect:/submissions/" + submission.getId();
    }
}