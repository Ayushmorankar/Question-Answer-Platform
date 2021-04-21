package com.example.ayush.questionanswerplatform.dto.answer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AnswerLikeRequest {
    @JsonProperty(required = true)
    @NotNull
    private Long answerId;
    @JsonProperty(required = true)
    @NotNull
    private Long userId;
}
