package com.example.ayush.questionanswerplatform.dto.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CommentRequest {
    @NotNull
    private Long answerId;
    @NotNull
    private Long userId;
    @NotBlank
    @Size(min = 5, max = 500)
    private String text;
}
