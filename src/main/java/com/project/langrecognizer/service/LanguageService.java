/**
 * The LanguageService interface defines methods for managing language entities.
 */
package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.model.Language;
import java.util.List;

public interface LanguageService {

    /**
     * Provides the status of the language service page.
     *
     * @return The status of the page.
     */
    String pageStatus();

    /**
     * Saves a language entity.
     *
     * @param language The language entity to be saved.
     * @return The saved language DTO.
     */
    LanguageDTO saveLanguage(Language language);

    /**
     * Saves a list of language entities.
     *
     * @param languages The list of language entities to be saved.
     * @return The list of saved language DTOs.
     */
    List<LanguageDTO> saveLanguages(List<Language> languages);

    /**
     * Retrieves all languages.
     *
     * @return A list of language DTOs.
     */
    List<LanguageDTO> getLanguages();

    /**
     * Retrieves a language by its ID.
     *
     * @param id The ID of the language to retrieve.
     * @return The language DTO.
     */
    LanguageDTO getLanguageById(Long id);

    /**
     * Retrieves a language by its name.
     *
     * @param name The name of the language to retrieve.
     * @return The language DTO.
     */
    LanguageDTO getLanguageByName(String name);

    /**
     * Deletes a language by its ID.
     *
     * @param id The ID of the language to delete.
     * @return A message indicating the deletion.
     */
    String deleteLanguage(Long id);

    /**
     * Updates a language.
     *
     * @param language The language entity with updated information.
     * @return The updated language DTO.
     */
    LanguageDTO updateLanguage(Language language);
}
