package org.example.petcode.dto.response;

import lombok.Data;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
}