package com.example.ayush.questionanswerplatform.dto.answer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AnswerRequest {
    @JsonProperty(required = true)
    @NotNull
    private Long questionId;
    @JsonProperty(required = true)
    @NotBlank
    @Size(min = 5, max = 500)
    private String text;
    @JsonProperty(required = true)
    @NotNull
    private Long userId;
}
