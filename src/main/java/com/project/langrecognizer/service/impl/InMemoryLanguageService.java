package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.model.Dictionary;
import com.project.langrecognizer.model.Language;
import com.project.langrecognizer.repository.LanguageRepository;
import com.project.langrecognizer.service.LanguageService;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryLanguageService implements LanguageService {

    //@Autowired
    //private LanguageRepository repository;
    @Override
    public String pageStatus()
    {
        return "Server is working";
    }

    public Language findNameOfLanguage(String text){
        //List<Dictionary> sfdads
        return new Language(text, "English");
    }
/*

    @Override
    public Language saveLanguage(Language language) {
        return repository.save(language);
    }

    @Override
    public List<Language> saveLanguages(List<Language> languages) {
        return repository.saveAll(languages);
    }

    @Override
    public List<Language> getLanguages() {
        return repository.findAll();
    }

    @Override
    public Language getLanguageById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Language getLanguageByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public String deleteLanguage(int id) {
        repository.deleteById(id);
        return "language removed!! " + id;
    }

    @Override
    public Language updateLanguage(Language language) {
        Language existingLanguage = repository.findById(language.getId()).orElse(null);
        existingLanguage.setName(language.getName());
        existingLanguage.setTextOfLanguage(language.getTextOfLanguage());
        return repository.save(existingLanguage);
    }*/
}
