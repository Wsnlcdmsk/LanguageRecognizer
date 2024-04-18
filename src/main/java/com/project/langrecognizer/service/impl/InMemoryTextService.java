/**
 * The InMemoryTextService class provides implementation for the TextService interface using in-memory storage.
 * It manages CRUD operations for Text entities, text retrieval based on tags, and sorting by language.
 */
package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.exception.ResourceNotFoundException;
import com.project.langrecognizer.mapper.TextMapper;
import com.project.langrecognizer.model.Text;
import com.project.langrecognizer.repository.TextRepository;
import com.project.langrecognizer.service.ExternalApiService;
import com.project.langrecognizer.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryTextService implements TextService {

    private TextRepository repository;
    private ExternalApiService externalApiService;
    private TextMapper mapper;
    private static final String NO_TEXT_EXIST_WITH_CONTENT = "No text found with content: ";
    private static final String NO_TEXT_EXIST_WITH_ID = "No text found with id: ";

    /**
     * Saves a text entity.
     *
     * @param text The text entity to be saved.
     * @return The saved text DTO.
     * @throws BadRequestException If no content is provided for the text.
     */
    @Override
    @LoggingAnnotation
    public TextDTO saveText(Text text) throws BadRequestException {
        if (text.getContent() == null) {
            throw new BadRequestException("No content provided");
        }
        repository.save(text);
        return mapper.toDTO(text);
    }

    /**
     * Saves a list of text entities.
     *
     * @param texts The list of text entities to be saved.
     * @return The list of saved text DTOs.
     * @throws BadRequestException If no texts are provided or if no content is provided for any text.
     */
    @Override
    @LoggingAnnotation
    public List<TextDTO> saveTexts(List<Text> texts) throws BadRequestException {
        if (texts.size() == 0) {
            throw new BadRequestException("No texts provided");
        }
        for (Text text : texts) {
            if (text.getContent() == null) {
                throw new BadRequestException("No content provided");
            }
        }
        repository.saveAll(texts);
        return mapper.toDTOs(texts);
    }

    /**
     * Retrieves all texts.
     *
     * @return A list of text DTOs.
     */
    @Override
    public List<TextDTO> getTexts() {
        return mapper.toDTOs(repository.findAll());
    }

    /**
     * Retrieves a text by its ID.
     *
     * @param id The ID of the text to retrieve.
     * @return The text DTO.
     * @throws ResourceNotFoundException If no text is found with the given ID.
     */
    @Override
    public TextDTO getTextById(Long id) throws ResourceNotFoundException {
        return mapper.toDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_TEXT_EXIST_WITH_ID + id)));
    }

    /**
     * Retrieves a text by its content.
     *
     * @param content The content of the text to retrieve.
     * @return The text DTO.
     * @throws ResourceNotFoundException If no text is found with the given content.
     */
    @Override
    public TextDTO getTextByContent(String content) throws ResourceNotFoundException {
        return mapper.toDTO(repository.findByContent(content)
                .orElseThrow(() -> new BadRequestException(NO_TEXT_EXIST_WITH_CONTENT + content)));
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
    public String deleteText(Long id) throws ResourceNotFoundException {
        Text text = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_TEXT_EXIST_WITH_ID + id));
        repository.deleteById(id);
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
    public TextDTO updateText(Text text) throws BadRequestException {
        if (text.getContent() == null) {
            throw new BadRequestException("No content provided");
        }
        Text existingText;
        try {
            existingText = repository.findById(text.getId())
                    .orElseThrow(() -> new ResourceNotFoundException(NO_TEXT_EXIST_WITH_ID + text.getId()));
        } catch (ResourceNotFoundException exception) {
            existingText = new Text();
            existingText.setId(text.getId());
        }
        existingText.setContent(text.getContent());
        existingText.setLanguage(text.getLanguage());
        existingText.setTags(text.getTags());
        repository.deleteById(text.getId());
        repository.save(existingText);
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
    public List<String> findTextsSortedByTag(String tag) throws BadRequestException {
        if (tag == null) {
            throw new BadRequestException("No content provided");
        }
        return repository.findTextsSortedByTag(tag);
    }

    /**
     * Retrieves texts sorted by a specific language.
     *
     * @param language The language to sort texts by.
     * @return A list of texts sorted by the specified language.
     * @throws BadRequestException If no content is provided for the language.
     */
    @Override
    public List<String> findTextsSortedByLanguage(String language) throws BadRequestException {
        if (language == null) {
            throw new BadRequestException("No content provided");
        }
        return repository.findTextsSortedByLanguage(language);
    }
}