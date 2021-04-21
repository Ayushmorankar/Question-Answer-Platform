package com.example.ayush.questionanswerplatform.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CompanyRequest {
    @NotBlank
    private String name;
}
