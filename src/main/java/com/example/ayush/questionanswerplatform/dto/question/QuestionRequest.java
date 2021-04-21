package com.example.ayush.questionanswerplatform.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
    @NotBlank(message = "Cannot be empty")
    @Size(min = 5, max = 500)
    private String text;
    private Set<@NotNull Long> companyIds;
    @NotEmpty
    private Set<@NotNull Long> subTopicIds;
    private Set<@NotNull Long> tagIds;
    @NotNull
    private Long userId;
}
