/**
 * DTO (Data Transfer Object) для текста.
 */
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

    /**
     * Содержание текста.
     */
    @NotBlank
    @Size(min = 3, max = 500)
    private String content;

    /**
     * Язык текста.
     */
    private Language language;

    /**
     * Список тегов, связанных с этим текстом.
     */
    private List<Tag> tags;
}
