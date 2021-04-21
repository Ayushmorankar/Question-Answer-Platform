package com.example.ayush.questionanswerplatform.mapper;

import com.example.ayush.questionanswerplatform.dto.answer.AnswerRequest;
import com.example.ayush.questionanswerplatform.dto.answer.AnswerResponse;
import com.example.ayush.questionanswerplatform.dto.comment.CommentResponse;
import com.example.ayush.questionanswerplatform.dto.answer.FilteredQuestionAnswerResponse;
import com.example.ayush.questionanswerplatform.models.Answer;
import com.example.ayush.questionanswerplatform.models.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
public class AnswerMapper {

    public AnswerResponse mapToAnswerResponse(Answer answer, List<CommentResponse> commentResponseList){
        return AnswerResponse.builder()
                .numberOfLikesOnAnswer(answer.getNumberOfLikes())
                .text(answer.getText())
                .comments(commentResponseList)
                .userId(answer.getUser().getId())
                .build();
    }

    public Answer mapAnswerRequestToAnswer(AnswerRequest answerRequest, User answeredBy){
        return Answer.builder()
                .text(answerRequest.getText())
                .comments(new HashSet<>())
                .user(answeredBy)
                .numberOfLikes(0)

                .build();
    }

    public FilteredQuestionAnswerResponse mapAnswerToFilteredQuestionAnswerResponse(Answer answer){
        return FilteredQuestionAnswerResponse.builder()
                .text(answer.getText())
                .numberOfLikesOnAnswer(answer.getNumberOfLikes())
                .answerId(answer.getId())
                .build();
    }
}
