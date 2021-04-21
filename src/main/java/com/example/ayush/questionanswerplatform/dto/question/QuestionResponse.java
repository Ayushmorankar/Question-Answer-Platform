package com.example.ayush.questionanswerplatform.dto.question;

import com.example.ayush.questionanswerplatform.dto.answer.AnswerResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponse {
    private String text;
    private List<AnswerResponse> answerResponses;
    private Integer numberOfLikesOnQuestion;
    private List<Long> companyIds;
    private List<Long> tagIds;
    private Set<Long> topicIds;
}
