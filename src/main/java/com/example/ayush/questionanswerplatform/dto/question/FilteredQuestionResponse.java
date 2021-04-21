package com.example.ayush.questionanswerplatform.dto.question;

import com.example.ayush.questionanswerplatform.dto.answer.FilteredQuestionAnswerResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilteredQuestionResponse {
    private Long questionId;
    private String text;
    private Integer numberOfLikesOnQuestion;
    private FilteredQuestionAnswerResponse mostLikedAnswer;
    private List<Long> companyIds;
    private List<Long> tagIds;
}
