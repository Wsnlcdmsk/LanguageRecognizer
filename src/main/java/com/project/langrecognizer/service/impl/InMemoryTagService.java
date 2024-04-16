package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.exception.ResourceNotFoundException;
import com.project.langrecognizer.mapper.TagMapper;
import com.project.langrecognizer.model.Language;
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
    private static final String NO_TAG_EXIST_WITH_NAME = "No tag found with name: ";
    private static final String NO_TAG_EXIST_WITH_ID = "No tag found with id: ";

    @Override
    @LoggingAnnotation
    public TagDTO saveTag(Tag tag) throws BadRequestException{
        if(tag.getName() == null){
            throw new BadRequestException("No name provided");
        }
        repository.save(tag);
        return mapper.toDTO(tag);
    }

    @Override
    @LoggingAnnotation
    public List<TagDTO> saveTags(List<Tag> tags) throws BadRequestException{
        for(Tag tag: tags){
            if(tag.getName() == null){
                throw new BadRequestException("No name provided");
            }
        }
        repository.saveAll(tags);
        return mapper.toDTOs(tags);
    }

    @Override
    public List<Tag> getTags() {
        return repository.findAll();
    }

    @Override
    public Tag getTagById(Long id) throws ResourceNotFoundException{
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(NO_TAG_EXIST_WITH_ID + id));
    }

    @Override
    public Tag getTagByName(String name) throws ResourceNotFoundException{
        return repository.findByName(name).
                orElseThrow(() -> new ResourceNotFoundException(NO_TAG_EXIST_WITH_NAME + name));
    }

    @Override
    @LoggingAnnotation
    public String deleteTag(Long id) throws ResourceNotFoundException{
        Tag tag = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(NO_TAG_EXIST_WITH_ID + id));
        repository.deleteById(id);
        return "tag removed!! " + id;
    }

    @Override
    @LoggingAnnotation
    public TagDTO updateTag(TagDTO tagDTO) throws BadRequestException{
        if(tagDTO.getName() == null){
            throw new BadRequestException("No name provided");
        }
        Tag existingTag = repository.findById(tagDTO.getId()).orElse(null);
        assert existingTag != null;
        existingTag.setName(tagDTO.getName());
        existingTag.setTexts(tagDTO.getTexts());
        repository.deleteById(tagDTO.getId());
        repository.save(existingTag);
        return tagDTO;
    }

}
