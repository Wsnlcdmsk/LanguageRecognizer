package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.exception.ResourceNotFoundException;
import com.project.langrecognizer.mapper.TextMapper;
import com.project.langrecognizer.model.Tag;
import com.project.langrecognizer.model.Text;
import com.project.langrecognizer.repository.TextRepository;
import com.project.langrecognizer.service.ExternalApiService;
import com.project.langrecognizer.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InMemoryTextService implements TextService {

    private TextRepository repository;
    private ExternalApiService externalApiService;
    private TextMapper mapper;
    private static final String NO_TEXT_EXIST_WITH_CONTENT = "No text found with name: ";
    private static final String NO_TEXT_EXIST_WITH_ID = "No text found with id: ";

    @Override
    @LoggingAnnotation
    public TextDTO saveText(Text text) throws BadRequestException {
        if (text.getContent() == null) {
            throw new BadRequestException("No content provided");
        }
        repository.save(text);
        return mapper.toDTO(text);
    }

    @Override
    @LoggingAnnotation
    public List<TextDTO> saveTexts(List<Text> texts) throws BadRequestException {
        if (texts.size()== 0) {
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

    @Override
    public List<TextDTO> getTexts() {
        return mapper.toDTOs(repository.findAll());
    }

    @Override
    public TextDTO getTextById(Long id) throws ResourceNotFoundException {
        return mapper.toDTO(repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(NO_TEXT_EXIST_WITH_ID + id)));
    }

    @Override
    public TextDTO getTextByContent(String content) throws ResourceNotFoundException {
        return mapper.toDTO(repository.findByContent(content).
                orElseThrow(() -> new ResourceNotFoundException(NO_TEXT_EXIST_WITH_CONTENT + content)));
    }

    @Override
    @LoggingAnnotation
    public String deleteText(final Long id) throws ResourceNotFoundException {
        Text text;
        text = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(NO_TEXT_EXIST_WITH_ID + id));
        repository.deleteById(id);
        return "text removed!! " + id;
    }

    @Override
    @LoggingAnnotation
    public TextDTO updateText(final Text text) throws BadRequestException {
        if (text.getContent() == null) {
            throw new BadRequestException("No content provided");
        }
        Text existingText;
        try {
            existingText = repository.findById(text.getId()).
                    orElseThrow(() -> new ResourceNotFoundException(NO_TEXT_EXIST_WITH_ID + text.getId()));
        } catch (ResourceNotFoundException exeption) {
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

    @Override
    public List<String> findTextsSortedByTag(String tag) throws BadRequestException {
        if (tag == null) {
            throw new BadRequestException("No content provided");
        }
        return repository.findTextsSortedByTag(tag);
    }

    @Override
    public List<String> findTextsSortedByLanguage(String language) throws BadRequestException {
        if (language == null) {
            throw new BadRequestException("No content provided");
        }
        return repository.findTextsSortedByLanguage(language);
    }
}