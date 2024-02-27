package com.project.langrecognizer.service;

import com.project.langrecognizer.model.Language;

import java.util.List;

public interface LanguageService {
String paigSatus();
Language findNameOfLanguage(List<String> text);
}
