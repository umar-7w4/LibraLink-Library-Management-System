package com.libralink.controller;

import com.libralink.entity.User;
import com.libralink.service.SecurityService;
import com.libralink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = securityService.login(username, password);

        Map<String, Object> response = new HashMap<>();
        if (isAuthenticated) {
            response.put("message", "User authenticated successfully");
        } else {
            response.put("message", "Authentication failed");
        }
        return ResponseEntity.ok(response);
    }
    
    
    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; 
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user) {
        userService.save(user); 
        return "redirect:/auth/login"; 
    }
    

    
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        if (principal != null) {
            Optional<User> userOpt = userService.findByUsername(principal.getName());
            if (userOpt.isPresent()) {
                model.addAttribute("user", userOpt.get());
            }
        
        }
        return "dashboard";
    }
    
    @GetMapping("/books")
    public String showBooksPage(Model model) {
        return "book"; 
    }
    
    @GetMapping("/members")
    public String showMembersPage(Model model) {
        return "member"; 
    }
    
    @GetMapping("/employees")
    public String showEmployeesPage(Model model) {
        return "employee"; 
    }
    
    @GetMapping("/checkouts")
    public String showCheckoutsPage(Model model) {
        return "checkout"; 
    }
    
    @GetMapping("/bookcopies")
    public String showBookCopiesPage(Model model) {
        return "bookcopy"; 
    }
    
    @GetMapping("/fines")
    public String showfinesPage(Model model) {
        return "fine"; 
    }
    
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println("Authentication: " + authentication);
            UserDetails currentUser = (UserDetails) authentication.getPrincipal();
            return ResponseEntity.ok(currentUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }

    
    
}
