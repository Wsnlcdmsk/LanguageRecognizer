package com.project.langrecognizer.mapper;

import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.model.Tag;
import com.project.langrecognizer.model.Text;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TextMapper {

    public TextDTO toDTO(final Text text){
        TextDTO textDTO = new TextDTO();
        textDTO.setId(text.getId());
        textDTO.setContent(text.getContent());
        textDTO.setLanguage(text.getLanguage());
        textDTO.setTags(text.getTags());
        return textDTO;
    }

    public Text toEntity(final TextDTO textDTO){
        Text text = new Text();
        text.setId(textDTO.getId());
        text.setContent(textDTO.getContent());
        text.setLanguage(textDTO.getLanguage());
        text.setTags(textDTO.getTags());
        return text;
    }

    public List<TextDTO> toDTOs(final List<Text> texts) {
        List<TextDTO> textDTOs = new ArrayList<>();
        for(Text text:texts) {
            textDTOs.add(this.toDTO(text));
        }
        return textDTOs;
    }

    public List<Text> toEntitys(final List<TextDTO> textDTOs) {
        List<Text> texts = new ArrayList<>();
        for(TextDTO textDTO:textDTOs) {
            texts.add(this.toEntity(textDTO));
        }
        return texts;
    }
}
