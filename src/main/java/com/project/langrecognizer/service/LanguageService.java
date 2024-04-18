package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.model.Language;

import java.util.List;

public interface LanguageService {
String pageStatus();
LanguageDTO saveLanguage(Language language);
List<LanguageDTO> saveLanguages(List<Language> languages);
List<LanguageDTO> getLanguages();
LanguageDTO getLanguageById(Long id);
LanguageDTO getLanguageByName(String name);
String deleteLanguage(Long id);
LanguageDTO updateLanguage(Language language);
}
