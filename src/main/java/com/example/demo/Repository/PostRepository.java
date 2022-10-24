package com.example.demo.Repository;

import com.example.demo.Models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    public Post findByNamepost(String namepost);
}
