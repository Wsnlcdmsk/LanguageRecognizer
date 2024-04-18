package com.project.langrecognizer.dto;

import com.project.langrecognizer.model.Text;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class TagDTO {

    private Long id;
    @NotBlank
    @Size(min = 3,max = 30)
    private String name;
    private List<Text> texts;
}
