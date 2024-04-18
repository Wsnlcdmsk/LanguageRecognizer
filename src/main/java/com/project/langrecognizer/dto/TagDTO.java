/**
 * DTO (Data Transfer Object) для тега.
 */
package com.project.langrecognizer.dto;

import com.project.langrecognizer.model.Text;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private Long id;

    /**
     * Название тега.
     */
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;

    /**
     * Список текстов, связанных с этим тегом.
     */
    private List<Text> texts;
}
