package com.project.langrecognizer.mapper;

import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.model.Language;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LanguageMapper {

    public LanguageDTO toDTO(final Language language) {
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setId(language.getId());
        languageDTO.setName(language.getName());
        languageDTO.setTexts(language.getTexts());
        return languageDTO;
    }

    public Language toEntity(final LanguageDTO languageDTO) {
        Language language = new Language();
        language.setId(languageDTO.getId());
        language.setName(languageDTO.getName());
        language.setTexts(languageDTO.getTexts());
        return language;
    }
    public List<LanguageDTO> toDTOs(final List<Language> languages) {
        List<LanguageDTO> languageDTOs = new ArrayList<>();
        for (Language language : languages) {
            languageDTOs.add(this.toDTO(language));
        }
        return languageDTOs;
    }

    public List<Language> toEntitys(final List<LanguageDTO> languageDTOs) {
        List<Language> languages = new ArrayList<>();
        for(LanguageDTO languageDTO:languageDTOs) {
            languages.add(this.toEntity(languageDTO));
        }
        return languages;
    }
}
