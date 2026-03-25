package org.example.membership.controller;

import org.example.membership.entity.User;
import org.example.membership.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*") // allow frontend calls
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("user", user);

        return response;
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> data) {

        String email = data.get("email");
        String password = data.get("password");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("user", user);

        return response;
    }

    // ✅ DELETE single
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    // ✅ DELETE all
    @DeleteMapping("/all")
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}