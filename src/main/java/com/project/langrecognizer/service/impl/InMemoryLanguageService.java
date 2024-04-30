/**
 * The InMemoryLanguageService class provides implementation for
 * LanguageService interface using in-memory storage.
 */
package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.aspect.RequestCounterAnnotation;
import com.project.langrecognizer.cache.Cache;
import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.exception.ResourceNotFoundException;
import com.project.langrecognizer.mapper.LanguageMapper;
import com.project.langrecognizer.model.Language;
import com.project.langrecognizer.repository.LanguageRepository;
import com.project.langrecognizer.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InMemoryLanguageService implements LanguageService {

    /** The repository for accessing the database
     * and performing CRUD operations on the Language entity. */
    private LanguageRepository languageRepository;

    /** The cache for storing frequently accessed languages. */
    private Cache<Language, Long> cache;

    /** The mapper for converting between
     * Language entities and DTOs. */
    private LanguageMapper mapper;

    /** The error message to be thrown when no language
     * is found with the given name. */
    private static final String NO_LANGUAGE_EXIST_WITH_NAME =
            "No language found with name: ";

    /** The error message to be thrown when no language
     * is found with the given id. */
    private static final String NO_LANGUAGE_EXIST_WITH_ID =
            "No language found with id: ";

    /** The error message to be thrown when no name
     * is provided for a new language. */
    private static final String NO_NAME_PROVIDED =
            "No name provided";

    /** The error message to be thrown when no language
     * is provided for an update. */
    private static final String NO_LANGUAGE_PROVIDED =
            "No language provided";


    /**
     * Checks if the server is working.
     *
     * @return A message indicating the server status.
     */
    @Override
    public String pageStatus() {
        return "Server is working";
    }

    /**
     * Saves a language entity.
     *
     * @param language The language entity to be saved.
     * @return The saved language DTO.
     * @throws BadRequestException If no name is provided for the language.
     */
    @Override
    @LoggingAnnotation
    public LanguageDTO saveLanguage(final Language language)
            throws BadRequestException {
        if (language.getName() == null) {
            throw new BadRequestException(NO_NAME_PROVIDED);
        }
        languageRepository.save(language);
        if (cache == null) {
            cache = new Cache<>();
        }
        cache.saveCached(language.getId(), language);
        return mapper.toDTO(language);
    }

    /**
     * Saves a list of language entities.
     * @param languages The list of language entities to be saved.
     * @return The list of saved language DTOs.
     * @throws BadRequestException If no languages are provided
     * or if no name is provided for any language.
     */
    @Override
    @LoggingAnnotation
    public List<LanguageDTO> saveLanguages(final List<Language> languages)
            throws BadRequestException {
        if (languages.isEmpty()) {
            throw new BadRequestException(NO_LANGUAGE_PROVIDED);
        }
        for (Language language : languages) {
            if (language.getName() == null) {
                throw new BadRequestException(NO_NAME_PROVIDED);
            }
            if (cache == null) {
                cache = new Cache<>();
            }
            cache.saveCached(language.getId(), language);
        }
        languageRepository.saveAll(languages);
        return mapper.toDTOs(languages);
    }

    /**
     * Retrieves all languages.
     *
     * @return A list of language DTOs.
     */
    @Override
    @RequestCounterAnnotation
    public List<LanguageDTO> getLanguages() {
        return mapper.toDTOs(languageRepository.findAll());
    }

    /**
     * Retrieves a language by its ID.
     * @param id The ID of the language to retrieve.
     * @return The language DTO.
     * @throws ResourceNotFoundException If no language
     * is found with the given ID.
     */
    @Override
    public LanguageDTO getLanguageById(final Long id)
            throws ResourceNotFoundException {
        if (cache == null) {
            cache = new Cache<>();
        }
        Optional<Language> cachedLanguage = cache.getCachedById(id);
        if (cachedLanguage.isPresent()) {
            return mapper.toDTO(cachedLanguage.get());
        }
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        NO_LANGUAGE_EXIST_WITH_NAME + id));
        cache.saveCached(id, language);
        return mapper.toDTO(language);
    }

    /**
     * Retrieves a language by its name.
     *
     * @param name The name of the language to retrieve.
     * @return The language DTO.
     * @throws ResourceNotFoundException If no language
     * is found with the given name.
     */
    @Override
    public LanguageDTO getLanguageByName(final String name)
            throws ResourceNotFoundException {
        return mapper.toDTO(languageRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(
                        NO_LANGUAGE_EXIST_WITH_NAME + name)));
    }

    /**
     * Deletes a language by its ID.
     * @param id The ID of the language to delete.
     * @return A message indicating the deletion.
     * @throws ResourceNotFoundException If no language
     * is found with the given ID.
     */
    @Override
    @LoggingAnnotation
    public String deleteLanguage(final Long id)
            throws ResourceNotFoundException {
        if(!languageRepository.existsById(id)) {
            throw new ResourceNotFoundException(NO_LANGUAGE_EXIST_WITH_ID + id);
        }
        languageRepository.deleteById(id);
        cache.deleteCachedById(id);
        return "language removed!! " + id;
    }
    /**
     * Updates a language entity with the provided information.
     * @param language The language entity with updated information.
     * @return The updated language DTO.
     * @throws BadRequestException       If no name
     * is provided for the language.
     * @throws ResourceNotFoundException If no language
     * is found with the given ID.
     */
    @Override
    @LoggingAnnotation
    public LanguageDTO updateLanguage(final Language language)
            throws BadRequestException {
        if (language.getName() == null) {
            throw new BadRequestException(NO_NAME_PROVIDED);
        }
        if (cache == null) {
            cache = new Cache<>();
        }
        Language existingLanguage;
        try {
            existingLanguage = languageRepository.findById(language.getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            NO_LANGUAGE_EXIST_WITH_ID + language.getId()));
        } catch (ResourceNotFoundException exception) {
            existingLanguage = new Language();
            existingLanguage.setId(language.getId());
        }
        existingLanguage.setName(language.getName());
        existingLanguage.setTexts(language.getTexts());
        languageRepository.deleteById(language.getId());
        cache.deleteCachedById(language.getId());
        cache.saveCached(language.getId(), existingLanguage);
        languageRepository.save(existingLanguage);
        return mapper.toDTO(language);
    }
}
