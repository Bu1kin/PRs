package com.example.demo.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String amount;
    private String timeamount;
    @OneToMany(mappedBy = "credit", fetch = FetchType.EAGER) // Создание связи многие-к-одному с каскадным удалением для таблицы сотрудников
    private Collection<Employee> employee;

    public Credit(String amount, String timeamount){
        this.amount = amount;
        this.timeamount = timeamount;
    }

    public Credit() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimeamount() {
        return timeamount;
    }

    public void setTimeamount(String timeamount) {
        this.timeamount = timeamount;
    }

    public Collection<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Collection<Employee> employee) {
        this.employee = employee;
    }
}
