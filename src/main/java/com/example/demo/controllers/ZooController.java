package com.example.demo.controllers;

import com.example.demo.Models.Zoo;
import com.example.demo.Repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ZooController {
    @Autowired
    ZooRepository zooRepository;

    @GetMapping("/zoo")
    public String main(Model model){
        Iterable<Zoo> listZoo = zooRepository.findAll();
        model.addAttribute("listAnimal", listZoo);
        return "zoo/index";
    }

    @GetMapping("zoo/add")
    public String zooAddView(Model model){
        return "zoo/add";
    }

    @PostMapping("zoo/add")
    public String zooAdd(@RequestParam String name, @RequestParam String description, @RequestParam Integer age, @RequestParam Integer mass, Model model){
        Zoo zoo = new Zoo(name, age, description, mass);
        zooRepository.save(zoo);
        return "redirect:/zoo";
    }

    @GetMapping("zoo/filter-direct")
    public String zooFilterDirect(@RequestParam String searchName, Model model){
        List<Zoo> zoo = zooRepository.findByName(searchName);
        model.addAttribute("listAnimal", zoo);
        return "zoo/filter";
    }

    @GetMapping("zoo/filter-contain")
    public String zooFilterContain(@RequestParam String searchName, Model model){
        List<Zoo> zoo = zooRepository.findByNameContaining(searchName);
        model.addAttribute("listAnimal", zoo);
        return "zoo/filter";
    }
}
