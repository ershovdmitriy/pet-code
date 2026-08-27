package org.example.petcode.service;

import lombok.RequiredArgsConstructor;
import org.example.petcode.dto.request.AuthRequest;
import org.example.petcode.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User register(AuthRequest request) {
        if (userService.existsByLogin(request.getLogin())) {
            throw new IllegalArgumentException("Логин уже занят");
        }
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        return userService.save(user);
    }
}
