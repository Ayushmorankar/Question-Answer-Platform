package com.example.ayush.questionanswerplatform.dao;

import com.example.ayush.questionanswerplatform.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicDao extends JpaRepository<Topic, Long> {
}
