package com.project.LangRecognizer.service;

import com.project.LangRecognizer.model.Language;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LanguageService {
String paigSatus();
Language findNameOfLanguage(List<String> Text);
}
