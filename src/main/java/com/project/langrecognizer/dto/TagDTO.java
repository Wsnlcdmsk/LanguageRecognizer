package com.project.langrecognizer.dto;

import com.project.langrecognizer.model.Text;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    @NotEmpty(message = "Tag's id can't be empty")
    @Size(min =  1, message = "Tag's id have to be more than 0")
    private Long id;
    @NotBlank(message = "Name of tag can't be empty")
    @Size(min = 1, max = 30, message = "Name of tag have to be between 3 and 30")
    private String name;
    @NotEmpty(message = "Tag's texts can't be empty")
    private List<Text> texts;
}
