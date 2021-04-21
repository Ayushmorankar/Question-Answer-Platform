package com.example.ayush.questionanswerplatform.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilteredQuestionAnswerResponse {
    private Long answerId;
    private String text;
    private Integer numberOfLikesOnAnswer;
}
