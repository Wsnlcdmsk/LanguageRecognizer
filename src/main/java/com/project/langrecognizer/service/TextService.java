package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.model.Text;

import java.util.List;

public interface TextService {
    TextDTO saveText(TextDTO textDTO);
    List<TextDTO> saveTexts(List<TextDTO> textsDTO);
    List<Text> getTexts();
    Text getTextById(Long id);
    Text getTextByContent(String content);
    String deleteText(Long id);
    TextDTO updateText(TextDTO textDTO);
    List<String> findTextsSortedByTag(String tag);
}
