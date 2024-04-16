package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.model.Language;

import java.util.List;

public interface LanguageService {
String pageStatus();
LanguageDTO saveLanguage(Language language);
List<LanguageDTO> saveLanguages(List<Language> languages);
List<Language> getLanguages();
Language getLanguageById(Long id);
Language getLanguageByName(String name);
String deleteLanguage(Long id);
LanguageDTO updateLanguage(LanguageDTO languageDTO);
}
