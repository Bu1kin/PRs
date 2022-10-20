package com.example.demo.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым!")
    private String surname;
    @NotBlank(message = "Поле не должно быть пустым!")
    private String name;
    @NotBlank(message = "Поле не должно быть пустым!")
    private String middle_name;
    @NotNull(message = "Поле не должно быть пустым!")
    @Positive(message = "Значение возраста должно быть положительным!")
    private Integer age;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Pattern(regexp = "\\+7\\([0-9][0-9][0-9]\\)[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]", message = "+7(XXX)XXX-XX-XX")
    private String phone;

    public Employee(String surname, String name, String middle_name, Integer age, String phone){
        this.surname = surname;
        this.name = name;
        this.middle_name = middle_name;
        this.age = age;
        this.phone = phone;
    }

    public Employee() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
