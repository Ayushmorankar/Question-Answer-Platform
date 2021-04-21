package com.example.ayush.questionanswerplatform.dto.question;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class QuestionLikeRequest {
    @NotNull
    private Long questionId;
    @NotNull
    private Long userId;
}
