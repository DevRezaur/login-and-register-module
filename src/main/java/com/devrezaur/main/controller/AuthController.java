package com.devrezaur.main.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.devrezaur.main.model.User;
import com.devrezaur.main.service.UserService;

@Controller
@RequestMapping({"/", "/auth"})
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping({"/", "/login"})
	public String login(@RequestParam(required = false) String error, Model model) {
		if(error != null && error.equals("true"))
			model.addAttribute("error", "Wrong Credentials");
		
		return "login.html";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN"))
            return "redirect:/admin/dashboard";
       
        return "redirect:/user/dashboard";
	}
	
	@GetMapping("/register")
	public String registrationPage(Model model) {
		model.addAttribute("user", new User());
		
		return "register-user.html";
	}
	
	@PostMapping("/register")
	public String registerUser(User user, RedirectAttributes ra) {
		if(userService.findUserByUsername(user.getUsername()) != null) {
			ra.addFlashAttribute("error", "User Already Exists");
			return "redirect:/auth/register";
		}
		userService.saveUser(user);
		ra.addFlashAttribute("success", "Registration Successful. Please Login");
		
		return "redirect:/auth/register";
	}
	
}
