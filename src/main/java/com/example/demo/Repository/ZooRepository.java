package com.example.demo.Repository;

import com.example.demo.Models.Zoo;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ZooRepository extends CrudRepository<Zoo, Long> {
    public List<Zoo> findByName(String name);

    public List<Zoo> findByNameContaining(String name);
}
