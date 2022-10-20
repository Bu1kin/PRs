package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

@Controller
public class homecontroller {

    @GetMapping("/")
    public String home(){
        return ("Home");
    }

    @GetMapping("/main/")
    public String Get(@RequestParam(name = "zn1") int zn1, @RequestParam(name = "zn2") int zn2, @RequestParam(name = "v") String v, Model model) {
        model.addAttribute("zn1", zn1);
        model.addAttribute("zn2", zn2);
        model.addAttribute("v", v);
        int result = 0;
        switch (v){
            case"+":{
                result = zn1 + zn2;
                break;
            }
            case"-":{
                result = zn1 - zn2;
                break;
            }
            case"*":{
                result = zn1 * zn2;
                break;
            }
            case"/":{
                result = zn1 / zn2;
                break;
            }
        }
        model.addAttribute("result", result);
        return "Result";
    }

    @PostMapping("/main/")
    public String Post(@RequestParam(name = "zn1") int zn1, @RequestParam(name = "zn2") int zn2, @RequestParam(name = "v") String v, Model model) {
        model.addAttribute("zn1", zn1);
        model.addAttribute("zn2", zn2);
        model.addAttribute("v", v);
        int result = 0;
        switch (v){
            case"+":{
                result = zn1 + zn2;
                break;
            }
            case"-":{
                result = zn1 - zn2;
                break;
            }
            case"*":{
                result = zn1 * zn2;
                break;
            }
            case"/":{
                result = zn1 / zn2;
                break;
            }
        }
        model.addAttribute("result", result);
        return "Result";
    }
}
