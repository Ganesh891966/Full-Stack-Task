package com.jobportal.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jobportal.entity.User;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // ✅ HOME
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // ✅ REGISTER PAGE
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // ✅ REGISTER LOGIC
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {

        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        userService.registerUser(user);

        model.addAttribute("success", "Account created successfully!");
        return "login";
    }

    // ✅ LOGIN PAGE
    @GetMapping("/login")
    public String showLoginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid email or password.");
        }

        if (logout != null) {
            model.addAttribute("success", "Logged out successfully.");
        }

        return "login";
    }

    // ✅ DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // ✅ PROFILE PAGE (SAFE FIX)
    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {

        if (principal == null) {
            return "redirect:/login"; // 🔥 prevents crash
        }

        Optional<User> optionalUser =
                userRepository.findByEmail(principal.getName());

        User user = optionalUser.orElse(new User());

        model.addAttribute("user", user);

        return "profile";
    }

    // ✅ UPDATE PROFILE (SAFE FIX)
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute User user, Principal principal) {

        if (principal == null) {
            return "redirect:/login";
        }

        Optional<User> optionalUser =
                userRepository.findByEmail(principal.getName());

        if (optionalUser.isPresent()) {

            User existingUser = optionalUser.get();

            existingUser.setFullName(user.getFullName());
            existingUser.setPhone(user.getPhone());
            existingUser.setLocation(user.getLocation());

            userRepository.save(existingUser);
        }

        return "redirect:/dashboard";
    }
}