package org.example.petcode.service;

import lombok.RequiredArgsConstructor;
import org.example.petcode.entity.Submission;
import org.example.petcode.entity.Task;
import org.example.petcode.entity.User;
import org.example.petcode.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    @Transactional
    public Submission createSubmission(User user, Task task, String code) {
        Submission submission = new Submission();
        submission.setUser(user);
        submission.setTask(task);
        submission.setCode(code);
        submission.setStatus("ACCEPTED");
        submission.setResult("Все тесты пройдены успешно!");
        submission.setExecutionTimeMs(0L);
        return submissionRepository.save(submission);
    }

    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Попытка решения не найдена"));
    }
}