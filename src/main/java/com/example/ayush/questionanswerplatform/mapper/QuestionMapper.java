package com.example.ayush.questionanswerplatform.mapper;

import com.example.ayush.questionanswerplatform.dto.answer.AnswerResponse;
import com.example.ayush.questionanswerplatform.dto.answer.FilteredQuestionAnswerResponse;
import com.example.ayush.questionanswerplatform.dto.question.FilteredQuestionResponse;
import com.example.ayush.questionanswerplatform.dto.question.QuestionRequest;
import com.example.ayush.questionanswerplatform.dto.question.QuestionResponse;
import com.example.ayush.questionanswerplatform.models.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class QuestionMapper {

    public Question mapToQuestion(QuestionRequest questionRequest, Set<Topic> topics, Set<Company> companies,
                                  Set<Tag> tags){

        return Question.builder()
                .topics(topics)
                .text(questionRequest.getText())
                .companies((companies.size()>0)?companies:null)
                .tags((tags.size()>0)?tags:null)
                .numberOfLikes(0)
                .createdAt(LocalDate.now())
                .build();
    }

    public FilteredQuestionResponse mapToFilteredQuestionResponse(Question question,
                                                                  FilteredQuestionAnswerResponse mostLikedAnswer,
                                                                  List<Long> tagIds,
                                                                  List<Long> companyIds){
        return FilteredQuestionResponse.builder()
                .text(question.getText())
                .questionId(question.getId())
                .numberOfLikesOnQuestion(question.getNumberOfLikes())
                .companyIds(companyIds)
                .mostLikedAnswer(mostLikedAnswer)
                .tagIds(tagIds)
                .build();
    }

    public QuestionResponse mapToQuestionResponse(Question question, List<AnswerResponse> answerResponses,
                                                  Set<Long> topicIds, List<Long> tagIds, List<Long> companyIds){

        return QuestionResponse.builder()
                .text(question.getText())
                .answerResponses(answerResponses)
                .companyIds(companyIds)
                .tagIds(tagIds)
                .topicIds(topicIds)
                .numberOfLikesOnQuestion(question.getNumberOfLikes())
                .build();
    }
}
