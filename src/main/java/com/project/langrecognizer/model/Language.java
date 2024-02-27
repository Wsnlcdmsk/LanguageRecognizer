package com.project.langrecognizer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Language {
    private List<String> textOfLanguage;
    private String nameOfLanguage;
}
