package com.project.langrecognizer.dto;
import com.project.langrecognizer.model.Text;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class LanguageDTO {
    private Long id;
    @NotBlank
    @Size(min = 3,max = 30)
    private String name;
    @NotBlank
    private List<Text> texts;
}
