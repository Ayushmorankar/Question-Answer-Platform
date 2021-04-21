package com.example.ayush.questionanswerplatform.dao;

import com.example.ayush.questionanswerplatform.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Long> {
}
