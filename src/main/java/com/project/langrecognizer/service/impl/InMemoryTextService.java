package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.exception.ResourceNotFoundException;
import com.project.langrecognizer.mapper.TextMapper;
import com.project.langrecognizer.model.Language;
import com.project.langrecognizer.model.Tag;
import com.project.langrecognizer.model.Text;
import com.project.langrecognizer.repository.TextRepository;
import com.project.langrecognizer.service.ExternalApiService;
import com.project.langrecognizer.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public TextDTO saveText(Text text) throws BadRequestException{
        if(text.getContent() == null){
            throw new BadRequestException("No content provided");
        }
        repository.save(text);
        return mapper.toDTO(text);
    }

    @Override
    @LoggingAnnotation
    public List<TextDTO> saveTexts(List<Text> texts) throws BadRequestException{
        for(Text text: texts){
            if(text.getContent() == null){
                throw new BadRequestException("No content provided");
            }
        }
        repository.saveAll(texts);
        return mapper.toDTOs(texts);
    }

    @Override
    public List<Text> getTexts() {
        return repository.findAll();
    }

    @Override
    public Text getTextById(Long id) throws ResourceNotFoundException{
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(NO_TEXT_EXIST_WITH_ID + id));
    }

    @Override
    public Text getTextByContent(String content) throws ResourceNotFoundException{
        Optional<Text> text = repository.findByContent(content);
        if(text.isPresent()) {
            return text.get();
        }
        Text externalText = new Text();
        List<Tag> tags = new ArrayList<>();
        List<Text> texts = new ArrayList<>();
        Tag tag = new Tag();
        externalText.setContent(content);
        externalText.setLanguage(externalApiService.detectLanguage(content));
        tag.setName("Unknown");
        texts.add(externalText);
        tag.setTexts(texts);
        tags.add(tag);
        externalText.setTags(tags);
        return Optional.of(externalText).
                orElseThrow(() -> new ResourceNotFoundException(NO_TEXT_EXIST_WITH_CONTENT + content));
    }

    @Override
    @LoggingAnnotation
    public String deleteText(Long id) throws ResourceNotFoundException{
        Text text = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(NO_TEXT_EXIST_WITH_ID + id));
        repository.deleteById(id);
        return "text removed!! " + id;
    }

    @Override
    @LoggingAnnotation
    public TextDTO updateText(TextDTO textDTO) throws BadRequestException{
        if(textDTO.getContent() == null){
            throw new BadRequestException("No content provided");
        }
        Text existingText = repository.findById(textDTO.getId()).orElse(null);
        assert existingText != null;
        existingText.setContent(textDTO.getContent());
        existingText.setLanguage(textDTO.getLanguage());
        existingText.setTags(textDTO.getTags());
        repository.deleteById(textDTO.getId());
        repository.save(existingText);
        return textDTO;
    }

    @Override
    public List<String> findTextsSortedByTag(String tag) throws BadRequestException{
        if(tag == null){
            throw new BadRequestException("No content provided");
        }
        return  repository.findTextsSortedByTag(tag);
    }

    @Override
    public List<String> findTextsSortedByLanguage(String language) throws BadRequestException{
        if(language == null){
            throw new BadRequestException("No content provided");
        }
        return repository.findTextsSortedByLanguage(language);
    }
}