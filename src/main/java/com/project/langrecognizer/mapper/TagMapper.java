package com.project.langrecognizer.mapper;

import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.model.Tag;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagMapper {
    public TagDTO toDTO(final @NonNull Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        tagDTO.setTexts(tag.getTexts());
        return tagDTO;
    }

    public Tag toEntity(final @NonNull TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(tagDTO.getId());
        tag.setName(tagDTO.getName());
        tag.setTexts(tagDTO.getTexts());
        return tag;
    }
    public List<TagDTO> toDTOs(final @NonNull List<Tag> tags) {
        List<TagDTO> tagDTOs = new ArrayList<>();
        TagDTO tagDTO = new TagDTO();
        for(Tag tag:tags) {
            tagDTO.setId(tag.getId());
            tagDTO.setName(tag.getName());
            tagDTO.setTexts(tag.getTexts());
            tagDTOs.add(tagDTO);
        }
        return tagDTOs;
    }

    public List<Tag> toEntitys(final @NonNull List<TagDTO> tagDTOs) {
        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();
        for(TagDTO tagDTO:tagDTOs) {
            tag.setId(tagDTO.getId());
            tag.setName(tagDTO.getName());
            tag.setTexts(tagDTO.getTexts());
            tags.add(tag);
        }
        return tags;
    }
}
