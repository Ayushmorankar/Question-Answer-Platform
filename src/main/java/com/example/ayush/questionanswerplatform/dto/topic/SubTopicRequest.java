package com.example.ayush.questionanswerplatform.dto.topic;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SubTopicRequest {
    @NotNull
    private Long topicId;
    @NotBlank
    private String subTopicName;
}
