package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.mapper.TagMapper;
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
    private TagMapper mapper;

    @Override
    public TagDTO saveTag(TagDTO tagDTO){
        repository.save(mapper.toEntity(tagDTO));
        return tagDTO;
    }

    @Override
    public List<TagDTO> saveTags(List<TagDTO> tagsDTO) {
        repository.saveAll(mapper.toEntitys(tagsDTO));
        return tagsDTO;
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
    public List<Tag> getTagByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public String deleteTag(Long id) {
        repository.deleteById(id);
        return "tag removed!! " + id;
    }

    @Override
    public TagDTO updateTag(TagDTO tagDTO){
        Tag existingTag = repository.findById(tagDTO.getId()).orElse(null);
        assert existingTag != null;
        existingTag.setName(tagDTO.getName());
        existingTag.setTexts(tagDTO.getTexts());
        repository.deleteById(tagDTO.getId());
        repository.save(existingTag);
        return tagDTO;
    }

}
