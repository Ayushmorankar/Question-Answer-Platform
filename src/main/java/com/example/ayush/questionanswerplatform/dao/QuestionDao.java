package com.example.ayush.questionanswerplatform.dao;

import com.example.ayush.questionanswerplatform.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionDao extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {
}
