package org.example.petcode.service;

import lombok.RequiredArgsConstructor;
import org.example.petcode.entity.User;
import org.example.petcode.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }
}