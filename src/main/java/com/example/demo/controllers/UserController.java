package com.example.demo.controllers;

import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public String userView(Model model){
        model.addAttribute("listUser", userRepository.findAll());
        return "/user/userView";
    }

    @GetMapping("/user/userEdit/{id}")
    public String userEditView(@PathVariable Long id, Model model){
        model.addAttribute("listRole", Role.values());
        model.addAttribute("oneUser", userRepository.findById(id).orElseThrow());
        return "/user/userEdit";
    }

    @PostMapping("/user/userEdit/{id}")
    public String userEdit(@PathVariable Long id,
                           @RequestParam String username,
                           @RequestParam String[] userRoles,
                           Model model){
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        user.getRoles().clear();
        for(String oneRole : userRoles){
            user.getRoles().add(Role.valueOf(oneRole));
        }
        userRepository.save(user);
        return "redirect:/user";
    }
}
