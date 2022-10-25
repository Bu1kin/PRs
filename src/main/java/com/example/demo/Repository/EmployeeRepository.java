package com.example.demo.Repository;

import com.example.demo.Models.Employee;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public List<Employee> findBySurname(String surname);
    public List<Employee> findBySurnameContaining(String surname);

//    public Employee findBySurnameAndNameAndMiddle_name(String surname, String name, String middle_name);
}
