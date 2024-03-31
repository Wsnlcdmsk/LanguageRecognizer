package com.project.langrecognizer.service;

import com.project.langrecognizer.model.Text;

import java.util.List;

public interface TextService {
    Text saveText(Text text);
    List<Text> saveTexts(List<Text> texts);
    List<Text> getTexts();
    Text getTextById(Long id);
    Text getTextByContent(String content);
    String deleteText(Long id);
    Text updateText(Text text);
}
