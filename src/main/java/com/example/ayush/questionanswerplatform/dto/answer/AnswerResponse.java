package com.example.ayush.questionanswerplatform.dto.answer;

import com.example.ayush.questionanswerplatform.dto.comment.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResponse {
    private String text;
    private Long userId;
    private Integer numberOfLikesOnAnswer;
    private List<CommentResponse> comments;
}
