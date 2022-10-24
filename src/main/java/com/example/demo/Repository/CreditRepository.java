package com.example.demo.Repository;

import com.example.demo.Models.Credit;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CreditRepository extends CrudRepository<Credit, Long> {
    public Credit findByAmount(String credit);
}
