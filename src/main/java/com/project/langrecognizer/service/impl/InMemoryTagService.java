package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.model.Tag;
import com.project.langrecognizer.repository.TagRepository;
import com.project.langrecognizer.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryTagService implements  TagService{

    private TagRepository repository;

    @Override
    public Tag saveTag(Tag tag) {
        return repository.save(tag);
    }

    @Override
    public List<Tag> saveTags(List<Tag> tags) {
        return repository.saveAll(tags);
    }

    @Override
    public List<Tag> getTags() {
        return repository.findAll();
    }

    @Override
    public Tag getTagById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Tag getTagByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public String deleteTag(Long id) {
        repository.deleteById(id);
        return "tag removed!! " + id;
    }

    @Override
    public Tag updateTag(Tag tag) {
        Tag existingTag = repository.findById(tag.getId()).orElse(null);
        assert existingTag != null;
        existingTag.setName(tag.getName());
        existingTag.setTexts(tag.getTexts());
        repository.deleteById(tag.getId());
        return repository.save(existingTag);
    }

}
