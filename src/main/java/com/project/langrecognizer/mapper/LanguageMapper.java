package com.project.langrecognizer.mapper;

import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.model.Language;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    public List<LanguageDTO> toDTOs(final @NonNull List<Language> languages) {
        List<LanguageDTO> languageDTOs = new ArrayList<>();
        LanguageDTO languageDTO = new LanguageDTO();
        for(Language language:languages) {
            languageDTO.setId(language.getId());
            languageDTO.setName(language.getName());
            languageDTO.setTexts(language.getTexts());
            languageDTOs.add(languageDTO);
        }
        return languageDTOs;
    }

    public List<Language> toEntitys(final @NonNull List<LanguageDTO> languageDTOs) {
        List<Language> languages = new ArrayList<>();
        Language language = new Language();
        for(LanguageDTO languageDTO:languageDTOs) {
            language.setId(languageDTO.getId());
            language.setName(languageDTO.getName());
            language.setTexts(languageDTO.getTexts());
            languages.add(language);
        }
        return languages;
    }
}
