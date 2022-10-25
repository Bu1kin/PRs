package com.example.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 150, message = "Поле должно содержать от 1 до 150 символов")
    private String brand;
    @NotBlank(message = "Поле не должно быть пустым!")
    private String model;
    @NotBlank(message = "Поле не должно быть пустым!")
    private String color;
    @NotBlank(message = "Поле не должно быть пустым!")
    private String country;
    @NotNull(message = "Поле не может быть пустым")
    private Integer weight;
    @ManyToMany                                                 // Создание связи многие-ко-многим для таблицы сотрудников
    @JoinTable (name="employee_car",                            // Создаём таблицу с заданным именем
            joinColumns=@JoinColumn (name="car_id"),            // Создём столбец, отвечающий за внешний ключ этой сущности
            inverseJoinColumns=@JoinColumn(name="employee_id")) // Создём столбец, отвечающий за внешний ключ другой сущности
    private List<Employee> employees;

    public Car(String brand, String model, String color, String country, Integer weight, List<Employee> employees){
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.country = country;
        this.weight = weight;
        this.employees = employees;
    }
    public Car() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
