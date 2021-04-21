package com.example.ayush.questionanswerplatform.mapper;

import com.example.ayush.questionanswerplatform.dto.comment.CommentRequest;
import com.example.ayush.questionanswerplatform.dto.comment.CommentResponse;
import com.example.ayush.questionanswerplatform.models.Comment;
import com.example.ayush.questionanswerplatform.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CommentMapper {

    public Comment mapCommentRequestToComment(CommentRequest commentRequest, User user){
        return Comment.builder()
                .createdAt(LocalDate.now())
                .text(commentRequest.getText())
                .user(user)
                .build();
    }

    public CommentResponse mapToCommentResponse(Comment comment){
        return CommentResponse.builder()
                .text(comment.getText())
                .userId(comment.getUser().getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
