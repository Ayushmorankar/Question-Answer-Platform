package com.example.ayush.questionanswerplatform.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CommentResponse {
    private String text;
    private LocalDate createdAt;
    private Long userId;
}
