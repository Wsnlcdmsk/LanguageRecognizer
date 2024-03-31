package com.project.langrecognizer.dto;
import com.project.langrecognizer.model.Text;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class LanguageDTO {
    @NotEmpty(message = "Language's id can't be empty")
    @Size(min =  1, message = "Language's id have to be more than 0")
    private Long id;
    @NotBlank(message = "Name of language can't be empty")
    @Size(min = 1, max = 30, message = "Name of language have to be between 3 and 30")
    private String name;
    @NotEmpty(message = "Language's texts can't be empty")
    private List<Text> texts;
}
