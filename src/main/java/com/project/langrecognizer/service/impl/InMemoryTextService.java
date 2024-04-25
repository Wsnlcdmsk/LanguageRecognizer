/**
 * The InMemoryTextService class provides implementation
 * for the TextService interface using in-memory storage.
 * It manages CRUD operations for Text entities,
 * text retrieval based on tags, and sorting by language.
 */
package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.exception.ResourceNotFoundException;
import com.project.langrecognizer.mapper.TextMapper;
import com.project.langrecognizer.model.Language;
import com.project.langrecognizer.model.Text;
import com.project.langrecognizer.repository.LanguageRepository;
import com.project.langrecognizer.repository.TextRepository;
import com.project.langrecognizer.service.ExternalApiService;
import com.project.langrecognizer.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InMemoryTextService implements TextService {

    /** The repository for accessing the database
     * and performing CRUD operations on the Text entity. */
    private TextRepository textRepository;

    /** The repository for accessing the database
     * and performing CRUD operations on the Language entity. */
    private LanguageRepository languageRepository;

    /** The external API service
     * for detectingthe language of a given text. */
    private ExternalApiService externalApiService;

    /** The mapper for converting between
     * Text entities and DTOs. */
    private TextMapper mapper;

    /** The error message to be thrown when no text
     * is found with the given content. */
    private static final String NO_TEXT_EXIST_WITH_CONTENT =
            "No text found with content: ";

    /** The error message to be thrown when no text
     * is found with the given id. */
    private static final String NO_TEXT_EXIST_WITH_ID =
            "No text found with id: ";

    /** The error message to be thrown when no content
     * is provided for a new text. */
    private static final String NO_CONTENT_PROVIDED =
            "No content provide";

    /** The error message to be thrown when no texts
     * are provided for an update. */
    private static final String NO_TEXTS_PROVIDED =
            "No texts provided";
    /** The error message to be thrown when no language
     * exist with this id. */
    private static final String NO_LANGUAGE_EXIST_WITH_ID =
            "No language exist wuth id";

    /** The error message to be thrown when no
     * language repository is null. */
    private static final String LANGUAGE_REPOSITORY_IS_NULL =
            "No language exist wuth id";

    /**
     * Saves a text entity.
     *
     * @param text The text entity to be saved.
     * @return The saved text DTO.
     * @throws BadRequestException If no content is provided for the text.
     */
    @Override
    @LoggingAnnotation
    public TextDTO saveText(final Text text) throws BadRequestException {
        if (text.getContent() == null) {
            throw new BadRequestException(NO_CONTENT_PROVIDED);
        }
        textRepository.save(text);
        return mapper.toDTO(text);
    }

    /**
     * Saves a list of text entities.
     *
     * @param texts The list of text entities to be saved.
     * @return The list of saved text DTOs.
     * @throws BadRequestException If no texts are provided
     * or if no content is provided for any text.
     */
    @Override
    @LoggingAnnotation
    public List<TextDTO> saveTexts(final List<Text> texts)
            throws BadRequestException {
        if (texts.isEmpty()) {
            throw new BadRequestException(NO_TEXTS_PROVIDED);
        }
        for (Text text : texts) {
            if (text.getContent() == null) {
                throw new BadRequestException(NO_CONTENT_PROVIDED);
            }
        }
        textRepository.saveAll(texts);
        return mapper.toDTOs(texts);
    }

    /**
     * Retrieves all texts.
     *
     * @return A list of text DTOs.
     */
    @Override
    public List<TextDTO> getTexts() {
        return mapper.toDTOs(textRepository.findAll());
    }

    /**
     * Retrieves a text by its ID.
     *
     * @param id The ID of the text to retrieve.
     * @return The text DTO.
     * @throws ResourceNotFoundException If no text is found with the given ID.
     */
    @Override
    public TextDTO getTextById(final Long id) throws ResourceNotFoundException {
        return mapper.toDTO(textRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        NO_TEXT_EXIST_WITH_ID + id)));
    }

    /**
     * Retrieves a text by its content.
     *
     * @param content The content of the text to retrieve.
     * @return The text DTO.
     * @throws ResourceNotFoundException If no text
     * is found with the given content.
     */
    @Override
    public TextDTO getTextByContent(final String content)
            throws ResourceNotFoundException {
        return mapper.toDTO(textRepository.findByContent(content)
                .orElseThrow(() -> new BadRequestException(
                        NO_TEXT_EXIST_WITH_CONTENT + content)));
    }

    /**
    * Deletes a text by its ID.
    *
    * @param id The ID of the text to delete.
    * @return A message indicating the deletion.
    * @throws ResourceNotFoundException If no text is found with the given ID.
    */
    @Override
    @LoggingAnnotation
    public String deleteText(final Long id) throws ResourceNotFoundException {
        Text text = textRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        NO_TEXT_EXIST_WITH_ID + id));
        textRepository.deleteById(id);
        return "Text removed: " + id;
    }

    /**
     * Updates a text.
     *
     * @param text The text entity with updated information.
     * @return The updated text DTO.
     * @throws BadRequestException If no content is provided for the text.
     */
    @Override
    @LoggingAnnotation
    public TextDTO updateText(final Text text) throws BadRequestException {
        if (text.getContent() == null) {
            throw new BadRequestException(NO_CONTENT_PROVIDED);
        }
        Text existingText;
        try {
            existingText = textRepository.findById(text.getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            NO_TEXT_EXIST_WITH_ID + text.getId()));
        } catch (ResourceNotFoundException exception) {
            existingText = new Text();
            existingText.setId(text.getId());
        }
        existingText.setContent(text.getContent());
        existingText.setLanguage(text.getLanguage());
        existingText.setTags(text.getTags());
        textRepository.deleteById(text.getId());
        textRepository.save(existingText);
        return mapper.toDTO(text);
    }

    /**
     * Retrieves texts sorted by a specific tag.
     *
     * @param tag The tag to sort texts by.
     * @return A list of texts sorted by the specified tag.
     * @throws BadRequestException If no content is provided for the tag.
     */
    @Override
    public List<String> findTextsSortedByTag(final String tag)
            throws BadRequestException {
        if (tag == null) {
            throw new BadRequestException(NO_CONTENT_PROVIDED);
        }
        return textRepository.findTextsSortedByTag(tag);
    }

    /**
     * Retrieves texts sorted by a specific language.
     *
     * @param language The language to sort texts by.
     * @return A list of texts sorted by the specified language.
     * @throws BadRequestException If no content is provided for the language.
     */
    @Override
    public List<String> findTextsSortedByLanguage(
            final String language) throws BadRequestException {
        if (language == null) {
            throw new BadRequestException(NO_CONTENT_PROVIDED);
        }
        return textRepository.findTextsSortedByLanguage(language);
    }

    /**
     * Добавляет список текстов к языку.
     *
     * @param texts Список текстов для добавления.
     * @param id Идентификатор языка.
     * @return Сообщение об ошибке или null, если операция выполнена успешно.
     * @throws BadRequestException Если язык с указанным идентификатором не существует.
     */
    public String addListOfTextToLanguage(List<Text> texts, Long id)
            throws BadRequestException{
        if(languageRepository == null){
            throw new BadRequestException(LANGUAGE_REPOSITORY_IS_NULL);
        }
        Optional<Language> languageOptional = languageRepository.findById(id);
        if(languageOptional.isEmpty()){
            throw new BadRequestException(NO_LANGUAGE_EXIST_WITH_ID + id);
        }
        Language language = languageOptional.get();
        texts.stream().filter(text -> language.getTexts().stream()
                        .noneMatch(pnc -> pnc.getContent().equals(text.getContent())))
                .forEach(text -> {
                    language.getTexts().add(text);
                    text.setLanguage(language);
                    textRepository.save(text);
                });
        languageRepository.save(language);
        return "The operation was successful";
    }
}
