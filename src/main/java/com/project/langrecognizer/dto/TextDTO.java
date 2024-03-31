package com.project.langrecognizer.dto;

import com.project.langrecognizer.model.Language;
import com.project.langrecognizer.model.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class TextDTO {
    @NotEmpty(message = "Text's id can't be empty")
    @Size(min =  1, message = "Text's id have to be more than 0")
    private Long id;
    @NotBlank(message = "Text content can't be empty")
    @Size(min = 50, message = "Minimum length of content must be 50 character 50")
    private String content;
    @NotEmpty(message = "Text's language can't be empty")
    private Language language;
    @NotEmpty(message = "Text's tags can't be empty")
    private List<Tag> tags;
}
