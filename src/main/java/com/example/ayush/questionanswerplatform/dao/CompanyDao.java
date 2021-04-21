package com.example.ayush.questionanswerplatform.dao;

import com.example.ayush.questionanswerplatform.models.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyDao extends CrudRepository<Company, Long> {

    public Optional<Company> findByName(String name);
}
