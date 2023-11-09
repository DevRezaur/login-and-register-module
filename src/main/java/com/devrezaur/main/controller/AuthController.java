package com.devrezaur.main.controller;

import com.devrezaur.main.model.User;
import com.devrezaur.main.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/login"})
    public String loginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && error.equals("true")) {
            model.addAttribute("error", "Invalid credentials!");
        }

        return "login.html";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        }

        return "redirect:/user/dashboard";
    }

    @GetMapping("/user/dashboard")
    public String userDashboardPage() {
        return "user-dashboard.html";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboardPage() {
        return "admin-dashboard.html";
    }

    @GetMapping("/user/register")
    public String userRegistrationPage() {
        return "register-user.html";
    }

    @GetMapping("/admin/register")
    public String adminRegistrationPage() {
        return "register-admin.html";
    }

    @PostMapping("/user/register")
    public String registerUser(User user, RedirectAttributes redirectAttributes) {
        if (userService.findUserByUsername(user.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("error", "User already exists with username: " + user.getUsername());
            return "redirect:/auth/register";
        }
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("success", "Registration successful");

        return "redirect:/user/register";
    }

    @PostMapping("/admin/register")
    public String registerAdmin(User user, RedirectAttributes redirectAttributes) {
        if (userService.findUserByUsername(user.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("error", "Admin already exists with username: " + user.getUsername());
            return "redirect:/auth/register";
        }
        userService.saveAdmin(user);
        redirectAttributes.addFlashAttribute("success", "Registration successful");

        return "redirect:/admin/register";
    }

}
