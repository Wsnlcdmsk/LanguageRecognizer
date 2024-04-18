package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.model.Text;

import java.util.List;

public interface TextService {
    TextDTO saveText(Text text);
    List<TextDTO> saveTexts(List<Text> texts);
    List<TextDTO> getTexts();
    TextDTO getTextById(Long id);
    TextDTO getTextByContent(String content);
    String deleteText(Long id);
    TextDTO updateText(Text text);
    List<String> findTextsSortedByTag(String tag);
    List<String> findTextsSortedByLanguage(String language);
}
