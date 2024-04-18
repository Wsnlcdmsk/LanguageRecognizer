package com.project.langrecognizer.mapper;

import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.model.Tag;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagMapper {
    public TagDTO toDTO(final Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        tagDTO.setTexts(tag.getTexts());
        return tagDTO;
    }

    public Tag toEntity(final TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(tagDTO.getId());
        tag.setName(tagDTO.getName());
        tag.setTexts(tagDTO.getTexts());
        return tag;
    }
    public List<TagDTO> toDTOs(final List<Tag> tags) {
        List<TagDTO> tagDTOs = new ArrayList<>();
        for (Tag tag : tags) {
            tagDTOs.add(this.toDTO(tag));
        }
        return tagDTOs;
    }

    public List<Tag> toEntitys(final List<TagDTO> tagDTOs) {
        List<Tag> tags = new ArrayList<>();
        for(TagDTO tagDTO:tagDTOs) {
            tags.add(this.toEntity(tagDTO));
        }
        return tags;
    }
}
