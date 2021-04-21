package com.example.ayush.questionanswerplatform.dao;

import com.example.ayush.questionanswerplatform.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
