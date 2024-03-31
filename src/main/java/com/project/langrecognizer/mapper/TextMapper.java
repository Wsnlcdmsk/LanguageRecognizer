package com.project.langrecognizer.mapper;

import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.model.Text;
import lombok.NonNull;
import org.springframework.stereotype.Component;

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
}
