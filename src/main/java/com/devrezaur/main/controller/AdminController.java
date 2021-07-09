package com.devrezaur.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.devrezaur.main.model.User;
import com.devrezaur.main.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "admin-dashboard.html";
	}
	
	@GetMapping("/register")
	public String registrationPage(Model model) {
		model.addAttribute("user", new User());
		
		return "register-admin.html";
	}
	
	@PostMapping("/register")
	public String registerUser(User user, RedirectAttributes ra) {
		if(userService.findUserByUsername(user.getUsername()) != null) {
			ra.addFlashAttribute("error", "Admin Already Exists");
			return "redirect:/admin/register";
		}
		userService.saveAdmin(user);
		ra.addFlashAttribute("success", "Registration Successful. Please Login");
		
		return "redirect:/admin/register";
	}

}
