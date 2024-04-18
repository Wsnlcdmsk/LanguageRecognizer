package com.project.langrecognizer.dto;

import com.project.langrecognizer.model.Language;
import com.project.langrecognizer.model.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class TextDTO {
    private Long id;
    @NotBlank
    @Size(min = 3,max = 500)
    private String content;
    private Language language;
    private List<Tag> tags;
}
