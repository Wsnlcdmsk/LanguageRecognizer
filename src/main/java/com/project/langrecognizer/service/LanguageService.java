package com.project.langrecognizer.service;

import com.project.langrecognizer.model.Language;

import java.util.List;

public interface LanguageService {
String pageStatus();
Language findNameOfLanguage(String text);
/*Language saveLanguage(Language language);
List<Language> saveLanguages(List<Language> languages);
List<Language> getLanguages();
Language getLanguageById(int id);
Language getLanguageByName(String name);
String deleteLanguage(int id);
Language updateLanguage(Language language);*/
}
