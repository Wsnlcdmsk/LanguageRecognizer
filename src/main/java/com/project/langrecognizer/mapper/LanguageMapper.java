/**
 * The LanguageMapper class provides methods to map
 * between Language entities and LanguageDTO data transfer objects.
 */
package com.project.langrecognizer.mapper;

import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.model.Language;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LanguageMapper {

    /**
     * Maps a Language entity to a LanguageDTO data transfer object.
     *
     * @param language The Language entity to be mapped.
     * @return The mapped LanguageDTO object.
     */
    public LanguageDTO toDTO(final Language language) {
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setId(language.getId());
        languageDTO.setName(language.getName());
        languageDTO.setTexts(language.getTexts());
        return languageDTO;
    }

    /**
     * Maps a LanguageDTO data transfer object to a Language entity.
     *
     * @param languageDTO The LanguageDTO object to be mapped.
     * @return The mapped Language entity.
     */
    public Language toEntity(final LanguageDTO languageDTO) {
        Language language = new Language();
        language.setId(languageDTO.getId());
        language.setName(languageDTO.getName());
        language.setTexts(languageDTO.getTexts());
        return language;
    }

    /**
     * Maps a list of Language entities to a list of
     * LanguageDTO data transfer objects.
     *
     * @param languages The list of Language entities to be mapped.
     * @return The list of mapped LanguageDTO objects.
     */
    public List<LanguageDTO> toDTOs(final List<Language> languages) {
        List<LanguageDTO> languageDTOs = new ArrayList<>();
        for (Language language : languages) {
            languageDTOs.add(this.toDTO(language));
        }
        return languageDTOs;
    }

    /**
     * Maps a list of LanguageDTO data transfer objects
     * to a list of Language entities.
     *
     * @param languageDTOs The list of LanguageDTO objects to be mapped.
     * @return The list of mapped Language entities.
     */
    public List<Language> toEntitys(final List<LanguageDTO> languageDTOs) {
        List<Language> languages = new ArrayList<>();
        for (LanguageDTO languageDTO : languageDTOs) {
            languages.add(this.toEntity(languageDTO));
        }
        return languages;
    }
}
