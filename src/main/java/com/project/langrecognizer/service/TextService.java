package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.model.Text;

import java.util.List;

public interface TextService {
    TextDTO saveText(Text text);
    List<TextDTO> saveTexts(List<Text> texts);
    List<Text> getTexts();
    Text getTextById(Long id);
    Text getTextByContent(String content);
    String deleteText(Long id);
    TextDTO updateText(TextDTO textDTO);
    List<String> findTextsSortedByTag(String tag);
    List<String> findTextsSortedByLanguage(String language);
}
