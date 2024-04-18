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
import com.project.langrecognizer.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InMemoryLanguageService implements LanguageService {

    private LanguageRepository languageRepository;
    private TextService textService;
    private Cache<Language, Long> cache;
    private LanguageMapper mapper;
    private static final String NO_LANGUAGE_EXIST_WITH_NAME = "No language found with name: ";
    private static final String NO_LANGUAGE_EXIST_WITH_ID = "No language found with id: ";

    @Autowired
    public InMemoryLanguageService(LanguageRepository languageRepository,TextService textService, LanguageMapper mapper) {
        this.languageRepository = languageRepository;
        this.textService = textService;
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
        if(cache == null) {
            cache = new Cache<>();
        }
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
            if(cache == null) {
                cache = new Cache<>();
            }
            cache.saveCached(language.getId(), language);
        }
        languageRepository.saveAll(languages);
        return mapper.toDTOs(languages);
    }

    @Override
    public List<LanguageDTO> getLanguages() {
        return mapper.toDTOs(languageRepository.findAll());
    }

    @Override
    public LanguageDTO getLanguageById(Long id) throws ResourceNotFoundException{
        Optional<Language> cachedLanguage = cache.getCachedById(id);
        if (cachedLanguage.isPresent()) {
            return mapper.toDTO(cachedLanguage.get());
        }
        Language language = languageRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(NO_LANGUAGE_EXIST_WITH_NAME + id));
        cache.saveCached(id, language);
        return mapper.toDTO(language);
    }

    @Override
    public LanguageDTO getLanguageByName(final String name) throws ResourceNotFoundException{
        return mapper.toDTO(languageRepository.findByName(name).
                orElseThrow(() -> new ResourceNotFoundException(NO_LANGUAGE_EXIST_WITH_NAME + name)));
    }

    @Override
    @LoggingAnnotation
    public String deleteLanguage(Long id) throws ResourceNotFoundException{

        Language language = languageRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(NO_LANGUAGE_EXIST_WITH_ID + id));
        languageRepository.deleteById(id);
        cache.deleteCachedById(id);
        return "language removed!! " + id;
    }

    @Override
    @LoggingAnnotation
    public LanguageDTO updateLanguage(Language language) throws BadRequestException{
        if(language.getName() == null ){
            throw new BadRequestException("No name provided");
        }
        if(cache == null) {
            cache = new Cache<>();
        }
        Language existingLanguage;
        try {
            existingLanguage = languageRepository.findById(language.getId())
                    .orElseThrow(() -> new ResourceNotFoundException(NO_LANGUAGE_EXIST_WITH_ID + language.getId()));
        }catch(ResourceNotFoundException exeption) {
            existingLanguage = new Language();
            existingLanguage.setId(language.getId());
        }
        existingLanguage.setName(language.getName());
        existingLanguage.setTexts(language.getTexts());
        languageRepository.deleteById(language.getId());
        cache.deleteCachedById(language.getId());
        cache.saveCached(language.getId(), existingLanguage);
        languageRepository.save(existingLanguage);
        return mapper.toDTO(language);
    }
}
