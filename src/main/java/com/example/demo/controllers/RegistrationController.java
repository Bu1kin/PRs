package com.example.demo.controllers;

import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String RegView(User user){
        return "/registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        if(userRepository.findByUsername(user.getUsername()) != null){
            model.addAttribute("error", "Такой пользователь уже существует!");
            return "/registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(null));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
}
