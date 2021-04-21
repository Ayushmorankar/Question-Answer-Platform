package com.example.ayush.questionanswerplatform.controller;

import com.example.ayush.questionanswerplatform.dto.question.FilteredQuestionResponse;
import com.example.ayush.questionanswerplatform.dto.question.QuestionLikeRequest;
import com.example.ayush.questionanswerplatform.dto.question.QuestionRequest;
import com.example.ayush.questionanswerplatform.dto.question.QuestionResponse;
import com.example.ayush.questionanswerplatform.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("v1/questions")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final String DATE_PATTERN = "yyyy-mm-dd";

    @PostMapping
    public ResponseEntity<Long> createQuestion(@Valid @RequestBody  QuestionRequest questionRequest){

        Long questionId = questionService.createQuestion(questionRequest);
        return new ResponseEntity<>(questionId, HttpStatus.CREATED);
    }

    @PostMapping("/like")
    public ResponseEntity<Void> likeQuestion(@Valid @RequestBody QuestionLikeRequest questionLikeRequest){
        questionService.likeQuestion(questionLikeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Get filtered list of questions",
            notes = "API to filter questions by parameters mentioned below",
            response = FilteredQuestionResponse.class
    )
    @GetMapping("/filter")
    public ResponseEntity<Set<FilteredQuestionResponse>> filterQuestions(
            @RequestParam(required = false) Optional<List<Long>> tagIds,
                                             @RequestParam(required = false) Optional<List<Long>> topicIds,
                                             @RequestParam(required = false) Optional<List<Long>> companyIds,
                                             @RequestParam(required = false) Optional<Integer> likes,
                                             @ApiParam(value = "Date on and after which questions were created"+
                                             " Date Format: yyyy-mm-dd")
                                             @RequestParam(required = false)
            @DateTimeFormat(pattern = DATE_PATTERN) Optional<String> afterDate){

        Set<FilteredQuestionResponse> responseList = questionService.
                filterQuestions(tagIds, topicIds, companyIds, likes, afterDate);
        return ResponseEntity.of(Optional.of(responseList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable Long id){
        QuestionResponse response = questionService.findQuestionById(id);
        return ResponseEntity.of(Optional.of(response));
    }
}
