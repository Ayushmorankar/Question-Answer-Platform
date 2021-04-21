package com.example.ayush.questionanswerplatform.dao;

import com.example.ayush.questionanswerplatform.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerDao extends JpaRepository<Answer, Long> {
}
