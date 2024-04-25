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
    /** The id of the user. */
    private Long id;

    /** The minimum length of a user name. */
    private static final int MIN_NAME_LENGHT = 3;

    /** The maximum length of a user name. */
    private static final int MAX_NAME_LENGHT = 30;


    /**
     * Название тега.
     */
    @NotBlank
    @Size(min = MIN_NAME_LENGHT, max = MAX_NAME_LENGHT)
    private String name;

    /**
     * Список текстов, связанных с этим тегом.
     */
    private List<Text> texts;
}
