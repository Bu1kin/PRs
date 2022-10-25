package com.example.demo.Repository;

import com.example.demo.Models.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    public List<Car> findByBrand(String brand);
    public List<Car> findByBrandContaining(String brand);

//    public Car findByBrandAndModel(String brand, String model);
}
