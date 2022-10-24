package com.example.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotBlank(message = "Поле не должно быть пустым!")
    private String surname;
    //@NotBlank(message = "Поле не должно быть пустым!")
    private String name;
    //@NotBlank(message = "Поле не должно быть пустым!")
    private String middle_name;
    //@NotNull(message = "Поле не должно быть пустым!")
    //@Positive(message = "Значение возраста должно быть положительным!")
    private Integer age;
    //@NotBlank(message = "Поле не должно быть пустым!")
    //@Pattern(regexp = "\\+7\\([0-9][0-9][0-9]\\)[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]", message = "+7(XXX)XXX-XX-XX")
    private String phone;

    @OneToOne(optional = true, cascade = CascadeType.ALL) // Создание связи один к одному с каскадным удалением для таблицы регистраций
    @JoinColumn(name="post_id")                           // Указываем столбец как внешний ключ
    private Post post;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)    // Ссылается на столбик employee из таблицы сотрудников и
    private Credit credit;                        // стратерия загрузок - EAGER (объекты коллекции сразу загружаются)

    @ManyToMany                                            // Создание связи многие-ко-многим для таблицы автомобилей
    @JoinTable(name="employee_car",                        // Создаём таблицу с заданным именем
            joinColumns=@JoinColumn(name="employee_id"),   // Создём столбец, отвечающий за внешний ключ этой сущности
            inverseJoinColumns=@JoinColumn(name="car_id")) // Создём столбец, отвечающий за внешний ключ другой сущности
    private List<Car> cars;

    public Employee(String surname, String name, String middle_name, Integer age, String phone, Post post, Credit credit){
        this.surname = surname;
        this.name = name;
        this.middle_name = middle_name;
        this.age = age;
        this.phone = phone;
        this.post = post;
        this.credit = credit;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

}
