package com.project.langrecognizer.dto;

import com.project.langrecognizer.model.Language;
import com.project.langrecognizer.model.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class TextDTO {
    private Long id;
    @NotBlank
    @Size(min = 3,max = 500)
    private String content;
    @NotBlank
    private Language language;
    @NotBlank
    private List<Tag> tags;
}
