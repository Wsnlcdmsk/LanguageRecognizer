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
    /** The id of the comment. */
    private Long id;

    /** The minimum length of a comment content. */
    private static final int MIN_CONTENT_LENGHT = 3;

    /** The maximum length of a comment content. */
    private static final int MAX_CONTENT_LENGHT = 500;


    /**
     * Содержание текста.
     */
    @NotBlank
    @Size(min = MIN_CONTENT_LENGHT, max = MAX_CONTENT_LENGHT)
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
