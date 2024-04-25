package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.mapper.TextMapper;
import com.project.langrecognizer.model.Language;
import com.project.langrecognizer.repository.TextRepository;
import com.project.langrecognizer.service.impl.InMemoryTextService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.exception.ResourceNotFoundException;

import com.project.langrecognizer.model.Tag;
import com.project.langrecognizer.model.Text;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InMemoryTextServiceTest {
    @Mock
    private TextRepository textRepository;
    @Mock
    private TextMapper textMapper;
    @InjectMocks
    private InMemoryTextService textService;

    private static Text text;
    private static TextDTO textDTO;
    private static List<TextDTO> textDTOs;
    private static TextMapper notMockTextMapper;
    private final String textContent = "content";
    private static final int NUM_OF_REPEATS = 5;

    @BeforeAll
    static void setUp(){
        text = new Text();
        notMockTextMapper = new TextMapper();
        text.setId((long) 1);
        text.setContent("tag content");
        Language language = new Language();
        language.setId((long) 1);
        language.setName("language name");
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < NUM_OF_REPEATS; i++) {
            Tag tag = new Tag();
            tag.setId((long) i);
            tag.setName("Name " + i);
            tag.setTexts(List.of(text));
            tags.add(tag);
        }
        text.setTags(tags);
        language.setTexts(List.of(text));
        text.setLanguage(language);
        textDTO = notMockTextMapper.toDTO(text);
    }

    @Test
    void testFindAllTexts_Valid() {
        List<Text> texts = new ArrayList<>();
        for (int i = 0; i < NUM_OF_REPEATS; i++) {
            Text text = new Text();
            text.setId((long) i);
            text.setContent("text " + i);
            text.setLanguage(new Language((long)i, ("language name " + i), null));
            texts.add(text);
        }
        List<TextDTO> textDtos = notMockTextMapper.toDTOs(texts);
        when(textRepository.findAll()).thenReturn(texts);
        doReturn(textDtos).when(textMapper).toDTOs(texts);

        List<TextDTO> result = textService.getTexts();

        assertEquals(NUM_OF_REPEATS, result.size());
        for (int i = 0; i < NUM_OF_REPEATS; i++) {
            assertEquals(i, result.get(i).getId());
            assertEquals(i, result.get(i).getLanguage().getId());
            assertEquals("language name " + i, result.get(i).getLanguage().getName());
            assertEquals("text " + i, result.get(i).getContent());
        }
    }
    @Test
    void testFindAllText_NoTextExist() {
        when(textRepository.findAll()).thenReturn(new ArrayList<>());

        List<TextDTO> result = textService.getTexts();

        assertEquals(0, result.size());
    }

    @Test
    void testFindTextByContent_Valid() {
        when(textRepository.findByContent(textContent)).thenReturn(Optional.of(text));
        when(textMapper.toDTO(text)).thenReturn(textDTO);

        Optional<TextDTO> result = Optional.of(textService.getTextByContent(textContent));

        assertTrue(result.isPresent());
        assertEquals(textDTO, result.get());
    }
    @Test
    void testFindTextById_Valid() {
        when(textRepository.findById((long) 1)).thenReturn(Optional.of(text));
        when(textMapper.toDTO(text)).thenReturn(textDTO);

        Optional<TextDTO> result = Optional.of(textService.getTextById((long) 1));

        assertTrue(result.isPresent());
        assertEquals(textDTO, result.get());
    }
    @Test
    void testSaveText_Valid() {
        when(textRepository.save(text)).thenReturn(text);
        when(textMapper.toDTO(text)).thenReturn(textDTO);

        TextDTO resultTextDTO = textService.saveText(text);

        verify(textRepository, times(1)).save(text);
        assertEquals(textDTO, resultTextDTO);
    }
    @Test
    void testSavesTexts_Valid() {
        List<Text> texts = new ArrayList<>();
        for(int i = 0; i < NUM_OF_REPEATS; i++)
        {
            Text tempText = new Text();
            tempText.setId((long) i);
            tempText.setContent(textContent + i);
            texts.add(tempText);
        }
        when(textRepository.saveAll(texts)).thenReturn(texts);
        when(textMapper.toDTOs(texts)).thenReturn(textDTOs);

        List<TextDTO> resultTextDTOs = textService.saveTexts(texts);

        verify(textRepository, times(1)).saveAll(texts);
        assertEquals(textDTOs, resultTextDTOs);
    }
    @Test
    void testSavesTexts_NotValidObject() {
        List<Text> texts = new ArrayList<>();
        assertThrows(BadRequestException.class, () -> textService.saveTexts(texts));
    }
    @Test
    void testUpdateText_Valid() {
        when(textRepository.save(text)).thenReturn(text);
        when(textMapper.toDTO(text)).thenReturn(textDTO);

        TextDTO resultTextDTO = textService.updateText(text);

        verify(textRepository, times(1)).save(text);
        assertEquals(textDTO, resultTextDTO);
    }

    @Test
    void testSaveText_NotValidObject() {
        Text text = new Text();
        assertThrows(BadRequestException.class, () -> textService.saveText(text));
    }

    @Test
    void testUpdateText_NotValidObject() {
        Text text = new Text();
        assertThrows(BadRequestException.class, () -> textService.updateText(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"getTextByContent",  "deleteText", "getTextById"})
    void testNoTextExists(String methodName) {
        when(textRepository.findByContent(textContent)).thenReturn(Optional.empty());
        switch (methodName) {
            case "getTextByContent": {
                assertThrows(BadRequestException.class, () -> textService.getTextByContent(textContent));
                break;
            }
            case "deleteText": {
                assertThrows(BadRequestException.class, () -> textService.deleteText(textService.
                        getTextByContent(textContent).getId()));
                break;
            }
            case "getTextById": {
                assertThrows(BadRequestException.class, () -> textService.getTextById(textService.
                        getTextByContent(textContent).getId()));
                break;
            }
        }
    }
    @Test
    void testSQLQueryRequest_NotValidObject(){
        assertThrows(BadRequestException.class, () -> textService.findTextsSortedByTag(null));
    }
    @Test
    void testJPQLQueryRequest_NotValidObject(){
        assertThrows(BadRequestException.class, () -> textService.findTextsSortedByLanguage(null));
    }
    @Test
    void testTextDelete_NotValidObject(){
        assertThrows(ResourceNotFoundException.class, () -> textService.deleteText((long) 100));
    }
    @Test
    void testAddListOfTextToLanguage1_NotValidObject(){
        assertThrows(NullPointerException.class, () -> textService.addListOfTextToLanguage(null, (long)1));
    }
}