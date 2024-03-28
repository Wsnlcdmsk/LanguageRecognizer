package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.model.Text;
import com.project.langrecognizer.repository.TextRepository;
import com.project.langrecognizer.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryTextService implements TextService {

    private TextRepository repository;

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
        return repository.findByContent(content);
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

    public List<String> findTagsSortedByText(String tag){
        return repository.findTagsSortedByText(tag);
    }
}
