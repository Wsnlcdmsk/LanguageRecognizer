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

    public TextDTO toDTO(final @NonNull Text text){
        TextDTO textDTO = new TextDTO();
        textDTO.setId(text.getId());
        textDTO.setContent(text.getContent());
        textDTO.setLanguage(text.getLanguage());
        textDTO.setTags(text.getTags());
        return textDTO;
    }

    public Text toEntity(final @NonNull TextDTO textDTO){
        Text text = new Text();
        text.setId(textDTO.getId());
        text.setContent(textDTO.getContent());
        text.setLanguage(textDTO.getLanguage());
        text.setTags(textDTO.getTags());
        return text;
    }

    public List<TextDTO> toDTOs(final @NonNull List<Text> texts) {
        List<TextDTO> textDTOs = new ArrayList<>();
        TextDTO textDTO = new TextDTO();
        for(Text text:texts) {
            textDTO.setId(text.getId());
            textDTO.setContent(text.getContent());
            textDTO.setTags(text.getTags());
            textDTO.setLanguage(text.getLanguage());
            textDTOs.add(textDTO);
        }
        return textDTOs;
    }

    public List<Text> toEntitys(final @NonNull List<TextDTO> textDTOs) {
        List<Text> texts = new ArrayList<>();
        Text text = new Text();
        for(TextDTO textDTO:textDTOs) {
            text.setId(textDTO.getId());
            text.setContent(textDTO.getContent());
            text.setTags(textDTO.getTags());
            text.setLanguage(textDTO.getLanguage());
            texts.add(text);
        }
        return texts;
    }
}
