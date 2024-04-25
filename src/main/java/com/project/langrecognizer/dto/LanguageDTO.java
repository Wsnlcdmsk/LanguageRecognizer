/**
 * DTO (Data Transfer Object) для языка.
 */
package com.project.langrecognizer.dto;

import com.project.langrecognizer.model.Text;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class LanguageDTO {
    /** The id of the tag. */
    private Long id;

    /** The minimum length of a tag name. */
    private static final int MIN_NAME_LENGHT = 3;

    /** The maximum length of a tag name. */
    private static final int MAX_NAME_LENGHT = 30;


    /**
     * Название языка.
     */
    @NotBlank
    @Size(min = MIN_NAME_LENGHT, max = MAX_NAME_LENGHT)
    private String name;

    /**
     * Список текстов на этом языке.
     */
    private List<Text> texts;
}
