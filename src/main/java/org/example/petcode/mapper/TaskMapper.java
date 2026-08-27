package org.example.petcode.mapper;

import org.example.petcode.dto.response.TaskResponse;
import org.example.petcode.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponse toResponse(Task task);

    List<TaskResponse> toResponseList(List<Task> tasks);
}