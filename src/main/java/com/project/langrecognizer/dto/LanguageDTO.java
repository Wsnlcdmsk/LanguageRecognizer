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
    private Long id;

    /**
     * Название языка.
     */
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;

    /**
     * Список текстов на этом языке.
     */
    private List<Text> texts;
}
