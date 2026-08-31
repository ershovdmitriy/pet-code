package org.example.petcode.controller;

import lombok.RequiredArgsConstructor;
import org.example.petcode.entity.Submission;
import org.example.petcode.service.SubmissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/submissions")
@RequiredArgsConstructor
public class SubmissionController {
    private final SubmissionService submissionService;

    @GetMapping("/{id}")
    public String showSubmission(@PathVariable Long id, Model model) {
        Submission submission = submissionService.getSubmissionById(id);
        model.addAttribute("submission", submission);
        return "submission-result";
    }
}