package com.example.ayush.questionanswerplatform.dao;

import com.example.ayush.questionanswerplatform.models.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TagDao extends CrudRepository<Tag, Long> {
    public Optional<Tag> findByName(String name);
}
