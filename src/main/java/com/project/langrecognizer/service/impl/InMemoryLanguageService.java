package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.cache.Cache;
import com.project.langrecognizer.model.Language;
import com.project.langrecognizer.repository.LanguageRepository;
import com.project.langrecognizer.repository.TextRepository;
import com.project.langrecognizer.service.LanguageService;
import com.project.langrecognizer.model.Text;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InMemoryLanguageService implements LanguageService {

    private LanguageRepository languageRepository;
    private TextRepository textRepository;
    private Cache<Language, Long> cache;

    @Autowired
    public InMemoryLanguageService(LanguageRepository languageRepository,TextRepository textRepository) {
        this.languageRepository = languageRepository;
        this.textRepository = textRepository;
        cache = new Cache<>();
    }
    @Override
    public String pageStatus()
    {
        return "Server is working";
    }

    @Override
    public Language saveLanguage(Language language) {
        Language savedLanguage = languageRepository.save(language);
        this.cache.saveCached(language.getId(), language);
        return savedLanguage;
    }

    @Override
    public List<Language> saveLanguages(List<Language> languages) {
        for(Language language:languages)
        {
            cache.saveCached(language.getId(), language);
        }
        return languageRepository.saveAll(languages);
    }

    @Override
    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public Language getLanguageById(Long id) {
        Optional<Language> cachedLanguage = cache.getCachedById(id);
        if (cachedLanguage.isPresent()) {
            return cachedLanguage.get();
        }
        Language language = languageRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        cache.saveCached(id, language);
        return language;
    }

    @Override
    public Language getLanguageByName(String name) {
        Language findLanguage = languageRepository.findByName(name);
        if (findLanguage != null) {
            cache.saveCached(findLanguage.getId(), findLanguage);
        }
        return findLanguage;
    }

    @Override
    public String deleteLanguage(Long id) {
        Language language = languageRepository.findById(id).orElseThrow(() -> new IllegalStateException("Error to delete"));
        for(Text text: language.getTexts())
        {
            textRepository.deleteById(text.getId());
        }
        languageRepository.deleteById(id);
        cache.deleteCachedById(id);
        return "language removed!! " + id;
    }

    @Override
    public Language updateLanguage(Language language) {
        Language existingLanguage = languageRepository.findById(language.getId()).orElse(null);
        assert existingLanguage != null;
        existingLanguage.setName(language.getName());
        existingLanguage.setTexts(language.getTexts());
        languageRepository.deleteById(language.getId());
        cache.deleteCachedById(language.getId());
        cache.saveCached(language.getId(), language);
        return languageRepository.save(existingLanguage);
    }
}
