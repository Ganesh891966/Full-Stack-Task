package org.example.membership.controller;

import org.example.membership.entity.User;
import org.example.membership.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Delete single user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    // Delete all users
    @DeleteMapping("/all")
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}