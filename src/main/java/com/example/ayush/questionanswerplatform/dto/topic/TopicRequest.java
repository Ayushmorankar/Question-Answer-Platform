package com.example.ayush.questionanswerplatform.dto.topic;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TopicRequest {
    @NotBlank
    private String name;
}
