/**
 * The TagMapper class provides methods to map between Tag entities and TagDTO data transfer objects.
 */
package com.project.langrecognizer.mapper;

import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.model.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagMapper {

    /**
     * Maps a Tag entity to a TagDTO data transfer object.
     *
     * @param tag The Tag entity to be mapped.
     * @return The mapped TagDTO object.
     */
    public TagDTO toDTO(final Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        tagDTO.setTexts(tag.getTexts());
        return tagDTO;
    }

    /**
     * Maps a TagDTO data transfer object to a Tag entity.
     *
     * @param tagDTO The TagDTO object to be mapped.
     * @return The mapped Tag entity.
     */
    public Tag toEntity(final TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(tagDTO.getId());
        tag.setName(tagDTO.getName());
        tag.setTexts(tagDTO.getTexts());
        return tag;
    }

    /**
     * Maps a list of Tag entities to a list of TagDTO data transfer objects.
     *
     * @param tags The list of Tag entities to be mapped.
     * @return The list of mapped TagDTO objects.
     */
    public List<TagDTO> toDTOs(final List<Tag> tags) {
        List<TagDTO> tagDTOs = new ArrayList<>();
        for (Tag tag : tags) {
            tagDTOs.add(this.toDTO(tag));
        }
        return tagDTOs;
    }

    /**
     * Maps a list of TagDTO data transfer objects to a list of Tag entities.
     *
     * @param tagDTOs The list of TagDTO objects to be mapped.
     * @return The list of mapped Tag entities.
     */
    public List<Tag> toEntitys(final List<TagDTO> tagDTOs) {
        List<Tag> tags = new ArrayList<>();
        for(TagDTO tagDTO : tagDTOs) {
            tags.add(this.toEntity(tagDTO));
        }
        return tags;
    }
}
