package com.example.demo.controllers;

import com.example.demo.Models.Car;
import com.example.demo.Models.Credit;
import com.example.demo.Models.Employee;
import com.example.demo.Models.Post;
import com.example.demo.Repository.CarRepository;
import com.example.demo.Repository.CreditRepository;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.PostRepository;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CreditRepository creditRepository;

    @GetMapping("/employee")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public String main(Model model){
        Iterable<Employee> listEmployee = employeeRepository.findAll();
        Iterable<Car> listCar = carRepository.findAll();
        Iterable<Post> listPost = postRepository.findAll();
        Iterable<Credit> listCredit = creditRepository.findAll();

        model.addAttribute("listEmployee", listEmployee);
        model.addAttribute("listCar", listCar);
        model.addAttribute("listPost", listPost);
        model.addAttribute("listCredit", listCredit);
        return "/employee/index";
    }

    @GetMapping("/employee/add")
    public String employeeAddView(Employee employee, Model model){
        Iterable<Credit> listCredit = creditRepository.findAll();
        Iterable<Post> listPost = postRepository.findAll();
        ArrayList<Post> postArrayList = new ArrayList<>();

        for(Post temp: listPost){
            if(temp.getEmployee() == null){
                postArrayList.add(temp);
            }
        }

        model.addAttribute("arrayListCredit", listCredit);
        model.addAttribute("arrayListPost", postArrayList);
        return "/employee/add";
    }

    @PostMapping("/employee/add")
    public String employeeAdd(@RequestParam String surname,
                              @RequestParam String name,
                              @RequestParam String middle_name,
                              @RequestParam Integer age,
                              @RequestParam String phone,
                              @RequestParam String namepost,
                              @RequestParam String amount,
                              Model model){
//        if(result.hasErrors())
//            return ("employee/add");

        Post post = postRepository.findByNamepost(namepost);
        Credit credit = creditRepository.findByAmount(amount.split(" срок погашения: ")[0]);

        Employee employee = new Employee(surname, name, middle_name, age, phone, post, credit);
        employeeRepository.save(employee);
        return ("redirect:/employee");
    }

    @GetMapping("/employee/filter-direct")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public String FilterDirect(@RequestParam String searchName, Model model){
        List<Employee> employee = employeeRepository.findBySurname(searchName);
        model.addAttribute("listEmployee", employee);
        return "/employee/filter";
    }

    @GetMapping("/employee/filter-contains")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public String FilterContains(@RequestParam String searchName, Model model){
        List<Employee> employee = employeeRepository.findBySurnameContaining(searchName);
        model.addAttribute("listEmployee", employee);
        return "/employee/filter";
    }

    @GetMapping("/employee/details/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
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

    @GetMapping("/employee/post")
    public String postAddView() {
        return ("employee/postAdd");
    }

    @PostMapping("/employee/post")
    public String postAdd(@RequestParam String namepost, @RequestParam String salary) {
        Post post = new Post(namepost, salary);
        postRepository.save(post);
        return ("redirect:/employee/add");
    }

    @GetMapping("/employee/credit")
    public String creditAddView() {
        return ("employee/creditAdd");
    }

    @PostMapping("/employee/credit")
    public String creditAdd(@RequestParam String amount, @RequestParam String timeamount) {
        Credit credit = new Credit(amount, timeamount);
        creditRepository.save(credit);
        return ("redirect:/employee/add");
    }

    @GetMapping("/employee/employeeCar")
    public String employeeCarView(Model model){
        Iterable<Employee> listEmployee = employeeRepository.findAll();
        Iterable<Car> listCar = carRepository.findAll();

        model.addAttribute("listEmployee", listEmployee);
        model.addAttribute("listCar", listCar);

        return "/employee/index";
    }

    @GetMapping("/employee/employeeCarAdd")
    public String employeeCarAddView(Model model) {

        Iterable<Employee> listEmployee = employeeRepository.findAll();
        Iterable<Car> listCar = carRepository.findAll();

        model.addAttribute(("listEmployee"), listEmployee);
        model.addAttribute(("listCar"), listCar);

        return ("employee/employeeCarAdd");
    }

    @PostMapping("/employee/employeeCarAdd")
    public String employeeCarAdd(@RequestParam String FIO, @RequestParam String bm, Model model) {
        Employee employee = employeeRepository.findById(Long.valueOf(FIO.split(" ")[0])).orElseThrow();
        Car car = carRepository.findById(Long.valueOf(bm.split(" ")[0])).orElseThrow();

//        Employee employee = employeeRepository.findBySurnameAndNameAndMiddle_name(surname, name, middle_name);
//        Car car = carRepository.findByBrandAndModel(brand, car_model);

        List<Car> carList = employee.getCars();
        carList.add(car);

        employee.setCars(carList);

        employeeRepository.save(employee);
        return ("redirect:/employee/employeeCar");
    }
}
