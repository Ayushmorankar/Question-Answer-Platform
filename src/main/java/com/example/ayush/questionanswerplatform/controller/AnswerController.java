package com.example.ayush.questionanswerplatform.controller;

import com.example.ayush.questionanswerplatform.dto.answer.AnswerLikeRequest;
import com.example.ayush.questionanswerplatform.dto.answer.AnswerRequest;
import com.example.ayush.questionanswerplatform.dto.comment.CommentRequest;
import com.example.ayush.questionanswerplatform.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/answers")
@AllArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<Long> createAnswer(@Valid @RequestBody AnswerRequest answerRequest){
        Long answerId = answerService.createAnswer(answerRequest);
        return new ResponseEntity<>(answerId, HttpStatus.CREATED);
    }

    @PostMapping("/like")
    public ResponseEntity<Void> likeAnswer(@Valid @RequestBody AnswerLikeRequest answerLikeRequest){
        answerService.likeAnswer(answerLikeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/comment")
    public ResponseEntity<Long> createComment(@Valid @RequestBody CommentRequest commentRequest){
        Long commentId = answerService.createComment(commentRequest);
        return new ResponseEntity<>(commentId, HttpStatus.CREATED);
    }
}
