package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.mapper.TextMapper;
import com.project.langrecognizer.model.Language;
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

    public TextDTO saveText(TextDTO textDTO) {
        repository.save(mapper.toEntity(textDTO));
        return textDTO;
    }

    @Override
    public List<TextDTO> saveTexts(List<TextDTO> textsDTO) {
        repository.saveAll(mapper.toEntitys(textsDTO));
        return textsDTO;
    }

    @Override
    public List<Text> getTexts() {
        return repository.findAll();
    }

    @Override
    public Text getTextById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Text getTextByContent(String content) {
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
        return externalText;
    }

    @Override
    public String deleteText(Long id) {
        repository.deleteById(id);
        return "text removed!! " + id;
    }

    @Override
    public TextDTO updateText(TextDTO textDTO) {
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
    public List<String> findTextsSortedByTag(String tag){
        return  repository.findTextsSortedByTag(tag);
    }
}
