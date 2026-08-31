package org.example.petcode.service;

import org.example.petcode.dto.response.TaskResponse;
import org.example.petcode.entity.Task;
import org.example.petcode.entity.TestCase;
import org.example.petcode.mapper.TaskMapper;
import org.example.petcode.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.example.petcode.repository.TestCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TestCaseRepository testCaseRepository;

    public Task findTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Элемент с id = " + id + " не найден"));
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.toResponseList(tasks);
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Элемент с id = " + id + " не найден"));
        return taskMapper.toResponse(task);
    }

    public List<TestCase> getPublicTestCases(Long taskId) {
        return testCaseRepository.findByTaskIdAndIsPublicTrueOrderBySortOrderAsc(taskId);
    }
}