package com.project.langrecognizer.service.impl;

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
    ExternalApiService externalApiService;

    public Text saveText(Text text) {
        return repository.save(text);
    }

    @Override
    public List<Text> saveTexts(List<Text> texts) {
        return repository.saveAll(texts);
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
    public Text updateText(Text text) {
        Text existingText = repository.findById(text.getId()).orElse(null);
        assert existingText != null;
        existingText.setContent(text.getContent());
        existingText.setLanguage(text.getLanguage());
        existingText.setTags(text.getTags());
        repository.deleteById(text.getId());
        return repository.save(existingText);
    }

}
