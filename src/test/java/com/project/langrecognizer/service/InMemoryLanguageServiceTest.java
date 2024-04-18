package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.exception.ResourceNotFoundException;
import com.project.langrecognizer.mapper.LanguageMapper;

import com.project.langrecognizer.model.Language;
import com.project.langrecognizer.model.Text;
import com.project.langrecognizer.repository.LanguageRepository;
import com.project.langrecognizer.service.impl.InMemoryLanguageService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InMemoryLanguageServiceTest {
    @Mock
    private LanguageRepository languageRepository;
    @Mock
    private LanguageMapper languageMapper;
    @InjectMocks
    private InMemoryLanguageService languageService;

    private static Language language;
    private static LanguageDTO languageDTO;
    private static List<LanguageDTO> languageDTOs;
    private static LanguageMapper notMockLanguageMapper;
    private final String languageName = "name";
    private static final int NUM_OF_REPEATS = 5;

    @BeforeAll
    static void setUp(){
    language = new Language();
    notMockLanguageMapper = new LanguageMapper();
    language.setId((long) 1);
    language.setName("Language name");
    List<Text> texts = new ArrayList<>();
    for (int i = 0; i < NUM_OF_REPEATS; i++) {
        Text text = new Text();
        text.setId((long) i);
        text.setContent("Content " + i);
        text.setLanguage(language);
        texts.add(text);
    }
    language.setTexts(texts);
    languageDTO = notMockLanguageMapper.toDTO(language);
    }

    @Test
    void testFindAllLanguages_Valid() {
        List<Language> languages = new ArrayList<>();
        for (int i = 0; i < NUM_OF_REPEATS; i++) {
            Language language = new Language();
            language.setId((long) i);
            language.setName("language " + i);
            languages.add(language);
        }
        List<LanguageDTO> languageDtos = notMockLanguageMapper.toDTOs(languages);
        when(languageRepository.findAll()).thenReturn(languages);
        doReturn(languageDtos).when(languageMapper).toDTOs(languages);

        List<LanguageDTO> result = languageService.getLanguages();

        assertEquals(NUM_OF_REPEATS, result.size());
        for (int i = 0; i < NUM_OF_REPEATS; i++) {
            assertEquals(i, result.get(i).getId());
            assertEquals("language " + i, result.get(i).getName());
        }
    }
    @Test
    void testFindAllLanguages_NoLanguageExist() {
        when(languageRepository.findAll()).thenReturn(new ArrayList<>());

        List<LanguageDTO> result = languageService.getLanguages();

        assertEquals(0, result.size());
    }

    @Test
    void testFindLanguageByName_Valid() {
        when(languageRepository.findByName(languageName)).thenReturn(Optional.of(language));
        when(languageMapper.toDTO(language)).thenReturn(languageDTO);

        Optional<LanguageDTO> result = Optional.of(languageService.getLanguageByName(languageName));

        assertTrue(result.isPresent());
        assertEquals(languageDTO, result.get());
    }
    @Test
    void testFindLanguageById_Valid() {
        when(languageRepository.findById((long) 1)).thenReturn(Optional.of(language));
        when(languageMapper.toDTO(language)).thenReturn(languageDTO);

        Optional<LanguageDTO> result = Optional.of(languageService.getLanguageById((long) 1));

        assertTrue(result.isPresent());
        assertEquals(languageDTO, result.get());
    }
   @Test
    void testSavesLanguages_Valid() {
        List<Language> languages = new ArrayList<>();
        for(int i = 0; i < NUM_OF_REPEATS; i++)
        {
            Language tempLanguage = new Language();
            tempLanguage.setId((long) i);
            tempLanguage.setName(languageName + i);
            languages.add(tempLanguage);
        }
       when(languageRepository.saveAll(languages)).thenReturn(languages);
       when(languageMapper.toDTOs(languages)).thenReturn(languageDTOs);

        List<LanguageDTO> resultLanguageDTOs = languageService.saveLanguages(languages);

        verify(languageRepository, times(1)).saveAll(languages);
        assertEquals(languageDTOs, resultLanguageDTOs);
    }

    void testSaveLanguage_Valid(){
        when(languageRepository.save(language)).thenReturn(language);
        when(languageMapper.toDTO(language)).thenReturn(languageDTO);

        LanguageDTO resultLanguageDTO = languageService.saveLanguage(language);

        verify(languageRepository, times(1)).save(language);
        assertEquals(languageDTO, resultLanguageDTO);
    }
    @Test
    void testUpdateLanguage_Valid() {
        when(languageRepository.save(language)).thenReturn(language);
        when(languageMapper.toDTO(language)).thenReturn(languageDTO);

        LanguageDTO resultLanguageDTO = languageService.updateLanguage(language);

        verify(languageRepository, times(1)).save(language);
        assertEquals(languageDTO, resultLanguageDTO);
    }

    @Test
    void testSaveLanguage_NotValidObject() {
        Language language = new Language();
        assertThrows(BadRequestException.class, () -> languageService.saveLanguage(language));
    }

    @Test
    void testUpdateLanguage_NotValidObject() {
        Language language = new Language();
        assertThrows(BadRequestException.class, () -> languageService.updateLanguage(language));
    }

    @ParameterizedTest
    @ValueSource(strings = {"getLanguageByName",  "deleteLanguage", "getLanguageById"})
    void testNoLanguageExists(String methodName) {
        when(languageRepository.findByName(languageName)).thenReturn(Optional.empty());
        switch (methodName) {
            case "getLanguageByName": {
                assertThrows(ResourceNotFoundException.class, () -> languageService.getLanguageByName(languageName));
                break;
            }
            case "deleteLanguage": {
                assertThrows(ResourceNotFoundException.class, () -> languageService.deleteLanguage(languageService.
                        getLanguageByName(languageName).getId()));
                break;
            }
            case "getLanguageById": {
                assertThrows(ResourceNotFoundException.class, () -> languageService.getLanguageById(languageService.
                        getLanguageByName(languageName).getId()));
                break;
            }
        }
    }
    void testDeleteLanguage_Valid(){

    }
}