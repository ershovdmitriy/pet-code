package org.example.petcode.dto.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}
