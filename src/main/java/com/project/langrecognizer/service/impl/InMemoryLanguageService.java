package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.cache.Cache;
import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.mapper.LanguageMapper;
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
    private LanguageMapper mapper;

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
    public LanguageDTO saveLanguage(LanguageDTO languageDTO) {
        languageRepository.save(mapper.toEntity(languageDTO));
        this.cache.saveCached(languageDTO.getId(), mapper.toEntity(languageDTO));
        return languageDTO;
    }

    @Override
    public List<LanguageDTO> saveLanguages(List<LanguageDTO> languagesDTO) {
        for(LanguageDTO languageDTO:languagesDTO)
        {
            cache.saveCached(languageDTO.getId(), mapper.toEntity(languageDTO));
        }
        return languagesDTO;
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
    public List<Language> getLanguageByName(String name) {
        return languageRepository.findByName(name);
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
    public LanguageDTO updateLanguage(LanguageDTO languageDTO) {
        Language existingLanguage = languageRepository.findById(languageDTO.getId()).orElse(null);
        assert existingLanguage != null;
        existingLanguage.setName(languageDTO.getName());
        existingLanguage.setTexts(languageDTO.getTexts());
        languageRepository.deleteById(languageDTO.getId());
        cache.deleteCachedById(languageDTO.getId());
        cache.saveCached(languageDTO.getId(), existingLanguage);
        languageRepository.save(existingLanguage);
        return languageDTO;
    }
}
