package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.cache.Cache;
import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.exception.ResourceNotFoundException;
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
    private static final String NO_LANGUAGE_EXIST_WITH_NAME = "No language found with name: ";
    private static final String NO_LANGUAGE_EXIST_WITH_ID = "No language found with id: ";

    @Autowired
    public InMemoryLanguageService(LanguageRepository languageRepository,TextRepository textRepository, LanguageMapper mapper) {
        this.languageRepository = languageRepository;
        this.textRepository = textRepository;
        this.mapper = mapper;
        cache = new Cache<>();
    }
    @Override
    public String pageStatus()
    {
        return "Server is working";
    }

    @Override
    @LoggingAnnotation
    public LanguageDTO saveLanguage(Language language) throws BadRequestException{
        if(language.getName() == null){
            throw new BadRequestException("No name provided");
        }
        languageRepository.save(language);
        cache.saveCached(language.getId(), language);
        return mapper.toDTO(language);
    }

    @Override
    @LoggingAnnotation
    public List<LanguageDTO> saveLanguages(List<Language> languages) throws BadRequestException{
        for(Language language:languages)
        {
            if(language.getName() == null){
                throw new BadRequestException("No name provided");
            }
            cache.saveCached(language.getId(), language);
        }
        languageRepository.saveAll(languages);
        return mapper.toDTOs(languages);
    }

    @Override
    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public Language getLanguageById(Long id) throws ResourceNotFoundException{
        Optional<Language> cachedLanguage = cache.getCachedById(id);
        if (cachedLanguage.isPresent()) {
            return cachedLanguage.get();
        }
        Language language = languageRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(NO_LANGUAGE_EXIST_WITH_NAME + id));
        cache.saveCached(id, language);
        return language;
    }

    @Override
    public Language getLanguageByName(final String name) throws ResourceNotFoundException{
        return languageRepository.findByName(name).
                orElseThrow(() -> new ResourceNotFoundException(NO_LANGUAGE_EXIST_WITH_NAME + name));
    }

    @Override
    @LoggingAnnotation
    public String deleteLanguage(Long id) throws ResourceNotFoundException{
        Language language = languageRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(NO_LANGUAGE_EXIST_WITH_ID + id));
        for(Text text: language.getTexts())
        {
            textRepository.deleteById(text.getId());
        }
        languageRepository.deleteById(id);
        cache.deleteCachedById(id);
        return "language removed!! " + id;
    }

    @Override
    @LoggingAnnotation
    public LanguageDTO updateLanguage(LanguageDTO languageDTO) throws BadRequestException{
        if(languageDTO.getName() == null){
            throw new BadRequestException("No name provided");
        }
        Language existingLanguage = languageRepository.findById(languageDTO.getId()).orElse(null);
        existingLanguage.setName(languageDTO.getName());
        existingLanguage.setTexts(languageDTO.getTexts());
        languageRepository.deleteById(languageDTO.getId());
        cache.deleteCachedById(languageDTO.getId());
        cache.saveCached(languageDTO.getId(), existingLanguage);
        languageRepository.save(existingLanguage);
        return languageDTO;
    }
}
