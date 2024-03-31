package com.project.langrecognizer.mapper;

import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.model.Language;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {

    public LanguageDTO toDTO(final @NonNull Language language) {
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setId(language.getId());
        languageDTO.setName(language.getName());
        languageDTO.setTexts(language.getTexts());
        return languageDTO;
    }

    public Language toEntity(final @NonNull LanguageDTO languageDTO) {
        Language language = new Language();
        language.setId(languageDTO.getId());
        language.setName(languageDTO.getName());
        language.setTexts(languageDTO.getTexts());
        return language;
    }
}
