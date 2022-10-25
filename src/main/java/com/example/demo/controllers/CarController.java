package com.example.demo.controllers;

import com.example.demo.Models.Car;
import com.example.demo.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyAuthority('MACHINE','ADMIN')")
public class CarController {
    @Autowired
    CarRepository carRepository;

    @GetMapping("/car")
    public String main(Model model){
        Iterable<Car> listCar = carRepository.findAll();
        model.addAttribute("listCar", listCar);
        return "/car/index";
    }

    @GetMapping("/car/add")
    public String AddView(Car car){
        return "car/add";
    }

    @PostMapping("/car/add")
    public String postAdd(@Valid Car car, BindingResult result){
        if(result.hasErrors())
            return ("car/add");
        carRepository.save(car);
        return ("redirect:/car");
    }

    @GetMapping("/car/filter-direct")
    public String FilterDirect(@RequestParam String searchName, Model model){
        List<Car> car = carRepository.findByBrand(searchName);
        model.addAttribute("listCar", car);
        return "/car/filter";
    }

    @GetMapping("/car/filter-contains")
    public String FilterContains(@RequestParam String searchName, Model model){
        List<Car> car = carRepository.findByBrandContaining(searchName);
        model.addAttribute("listCar", car);
        return "/car/filter";
    }

    @GetMapping("/car/details/{id}")
    public String CarDetails(Model model, @PathVariable Long id){
        Car car = carRepository.findById(id).orElseThrow();
        model.addAttribute("listCar", car);
        return("/car/details");
    }

    @GetMapping("/car/del/{id}")
    public String CarDelete(@PathVariable Long id) {
        carRepository.deleteById(id); //Удаление записи по ID
        return("redirect:/car"); //Перенаправление на страницу машин
    }

    @GetMapping("/car/edit/{id}")
    public String CarEdit(Model model, @PathVariable Long id, Car car_) {
        Car car = carRepository.findById(id).orElseThrow(); //Поиск записи по ID
        model.addAttribute("listCar", car); //Передаём в модель запись
        return("/car/edit"); //Отображение страницы редактирования
    }

    @PostMapping("/car/edit/{id}")
    public String CarEdit(@Valid Car car, BindingResult result) {
        if(result.hasErrors())
            return ("car/edit");
        carRepository.save(car); //Сохранение изменений
        return("redirect:/car/details/" + car.getId()); //Перенаправление на страницу деталей
    }
}
