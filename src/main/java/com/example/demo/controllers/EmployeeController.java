package com.example.demo.controllers;

import com.example.demo.Models.Employee;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public String main(Model model){
        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute("listEmployee", listEmployee);
        return "/employee/index";
    }

    @GetMapping("/employee/add")
    public String AddView(Employee employee){
        return "/employee/add";
    }

    @PostMapping("/employee/add")
    public String postAdd(@Valid Employee employee, BindingResult result){
        if(result.hasErrors())
            return ("employee/add");
        employeeRepository.save(employee);
        return ("redirect:/employee");
    }

    @GetMapping("/employee/filter-direct")
    public String FilterDirect(@RequestParam String searchName, Model model){
        List<Employee> employee = employeeRepository.findBySurname(searchName);
        model.addAttribute("listEmployee", employee);
        return "/employee/filter";
    }

    @GetMapping("/employee/filter-contains")
    public String FilterContains(@RequestParam String searchName, Model model){
        List<Employee> employee = employeeRepository.findBySurnameContaining(searchName);
        model.addAttribute("listEmployee", employee);
        return "/employee/filter";
    }

    @GetMapping("/employee/details/{id}")
    public String EmployeeDetails(Model model, @PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("listEmployee", employee);
        return ("/employee/details");
    }

    @GetMapping("employee/del/{id}")
    public String EmployeeDelete(@PathVariable Long id) {
        employeeRepository.deleteById(id); //Удаление записи по ID
        return("redirect:/employee"); //Перенаправление на страницу машин
    }

    @GetMapping("/employee/edit/{id}")
    public String EmployeeEdit(Model model, @PathVariable Long id, Employee employee_) {
        Employee employee = employeeRepository.findById(id).orElseThrow(); //Поиск записи по ID
        model.addAttribute("listEmployee", employee); //Передаём в модель запись
        return("/employee/edit"); //Отображение страницы редактирования
    }

    @PostMapping("/employee/edit/{id}")
    public String EmployeeEdit(@Valid Employee employee, BindingResult result) {
        if(result.hasErrors())
            return ("employee/edit");
        employeeRepository.save(employee); //Сохранение изменений
        return("redirect:/employee/details/" + employee.getId()); //Перенаправление на страницу деталей
    }
}
