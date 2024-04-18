/**
 * The TextMapper class provides methods to map between Text entities and TextDTO data transfer objects.
 */
package com.project.langrecognizer.mapper;

import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.model.Text;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TextMapper {

    /**
     * Maps a Text entity to a TextDTO data transfer object.
     *
     * @param text The Text entity to be mapped.
     * @return The mapped TextDTO object.
     */
    public TextDTO toDTO(final Text text){
        TextDTO textDTO = new TextDTO();
        textDTO.setId(text.getId());
        textDTO.setContent(text.getContent());
        textDTO.setLanguage(text.getLanguage());
        textDTO.setTags(text.getTags());
        return textDTO;
    }

    /**
     * Maps a TextDTO data transfer object to a Text entity.
     *
     * @param textDTO The TextDTO object to be mapped.
     * @return The mapped Text entity.
     */
    public Text toEntity(final TextDTO textDTO){
        Text text = new Text();
        text.setId(textDTO.getId());
        text.setContent(textDTO.getContent());
        text.setLanguage(textDTO.getLanguage());
        text.setTags(textDTO.getTags());
        return text;
    }

    /**
     * Maps a list of Text entities to a list of TextDTO data transfer objects.
     *
     * @param texts The list of Text entities to be mapped.
     * @return The list of mapped TextDTO objects.
     */
    public List<TextDTO> toDTOs(final List<Text> texts) {
        List<TextDTO> textDTOs = new ArrayList<>();
        for(Text text : texts) {
            textDTOs.add(this.toDTO(text));
        }
        return textDTOs;
    }

    /**
     * Maps a list of TextDTO data transfer objects to a list of Text entities.
     *
     * @param textDTOs The list of TextDTO objects to be mapped.
     * @return The list of mapped Text entities.
     */
    public List<Text> toEntitys(final List<TextDTO> textDTOs) {
        List<Text> texts = new ArrayList<>();
        for(TextDTO textDTO : textDTOs) {
            texts.add(this.toEntity(textDTO));
        }
        return texts;
    }
}
