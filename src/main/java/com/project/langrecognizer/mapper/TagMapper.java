package com.project.langrecognizer.mapper;

import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.model.Tag;
import lombok.NonNull;
import org.springframework.stereotype.Component;

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

}
