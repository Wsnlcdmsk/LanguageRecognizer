package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.exception.ResourceNotFoundException;

import com.project.langrecognizer.mapper.TagMapper;
import com.project.langrecognizer.model.Tag;
import com.project.langrecognizer.model.Text;
import com.project.langrecognizer.repository.TagRepository;
import com.project.langrecognizer.service.impl.InMemoryTagService;
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
class InMemoryTagServiceTest {
    @Mock
    private TagRepository tagRepository;
    @Mock
    private TagMapper tagMapper;
    @InjectMocks
    private InMemoryTagService tagService;

    private static Tag tag;
    private static TagDTO tagDTO;
    private static List<TagDTO> tagDTOs;
    private static TagMapper notMockTagMapper;
    private final String tagName = "name";
    private static final int NUM_OF_REPEATS = 5;

    @BeforeAll
    static void setUp() {
        tag = new Tag();
        notMockTagMapper = new TagMapper();
        tag.setId((long) 1);
        tag.setName("tag name");
        List<Text> texts = new ArrayList<>();
        for (int i = 0; i < NUM_OF_REPEATS; i++) {
            Text text = new Text();
            text.setId((long) i);
            text.setContent("Content " + i);
            text.setTags(List.of(tag));
            texts.add(text);
        }
        tag.setTexts(texts);
        tagDTO = notMockTagMapper.toDTO(tag);
    }

    @Test
    void testFindAllTags_Valid() {
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < NUM_OF_REPEATS; i++) {
            Tag tag = new Tag();
            tag.setId((long) i);
            tag.setName("tag " + i);
            tags.add(tag);
        }
        List<TagDTO> tagDtos = notMockTagMapper.toDTOs(tags);
        when(tagRepository.findAll()).thenReturn(tags);
        doReturn(tagDtos).when(tagMapper).toDTOs(tags);

        List<TagDTO> result = tagService.getTags();

        assertEquals(NUM_OF_REPEATS, result.size());
        for (int i = 0; i < NUM_OF_REPEATS; i++) {
            assertEquals(i, result.get(i).getId());
            assertEquals("tag " + i, result.get(i).getName());
        }
    }

    @Test
    void testFindAllTag_NoTagExist() {
        when(tagRepository.findAll()).thenReturn(new ArrayList<>());

        List<TagDTO> result = tagService.getTags();

        assertEquals(0, result.size());
    }

    @Test
    void testFindTagByName_Valid() {
        when(tagRepository.findByName(tagName)).thenReturn(Optional.of(tag));
        when(tagMapper.toDTO(tag)).thenReturn(tagDTO);

        Optional<TagDTO> result = Optional.of(tagService.getTagByName(tagName));

        assertTrue(result.isPresent());
        assertEquals(tagDTO, result.get());
    }

    @Test
    void testFindTagById_Valid() {
        when(tagRepository.findById((long) 1)).thenReturn(Optional.of(tag));
        when(tagMapper.toDTO(tag)).thenReturn(tagDTO);

        Optional<TagDTO> result = Optional.of(tagService.getTagById((long) 1));

        assertTrue(result.isPresent());
        assertEquals(tagDTO, result.get());
    }

    @Test
    void testSaveTag_Valid() {
        when(tagRepository.save(tag)).thenReturn(tag);
        when(tagMapper.toDTO(tag)).thenReturn(tagDTO);

        TagDTO resultTagDTO = tagService.saveTag(tag);

        verify(tagRepository, times(1)).save(tag);
        assertEquals(tagDTO, resultTagDTO);
    }

    @Test
    void testSavesTags_Valid() {
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < NUM_OF_REPEATS; i++) {
            Tag tempTag = new Tag();
            tempTag.setId((long) i);
            tempTag.setName(tagName + i);
            tags.add(tempTag);
        }
        when(tagRepository.saveAll(tags)).thenReturn(tags);
        when(tagMapper.toDTOs(tags)).thenReturn(tagDTOs);

        List<TagDTO> resultTagDTOs = tagService.saveTags(tags);

        verify(tagRepository, times(1)).saveAll(tags);
        assertEquals(tagDTOs, resultTagDTOs);
    }

    @Test
    void testSavesTags_NotValidObject() {
        List<Tag> tags = new ArrayList<>();
        assertThrows(BadRequestException.class, () -> tagService.saveTags(tags));
    }

    @Test
    void testUpdateTag_Valid() {
        when(tagRepository.save(tag)).thenReturn(tag);
        when(tagMapper.toDTO(tag)).thenReturn(tagDTO);

        TagDTO resultTagDTO = tagService.updateTag(tag);

        verify(tagRepository, times(1)).save(tag);
        assertEquals(tagDTO, resultTagDTO);
    }

    @Test
    void testSaveTag_NotValidObject() {
        Tag tag = new Tag();
        assertThrows(BadRequestException.class, () -> tagService.saveTag(tag));
    }

    @Test
    void testUpdateTag_NotValidObject() {
        Tag tag = new Tag();
        assertThrows(BadRequestException.class, () -> tagService.updateTag(tag));
    }

    @ParameterizedTest
    @ValueSource(strings = {"getTagByName", "deleteTag", "getTagById"})
    void testNoTagExists(String methodName) {
        when(tagRepository.findByName(tagName)).thenReturn(Optional.empty());
        switch (methodName) {
            case "getTagByName":
                assertThrows(ResourceNotFoundException.class, () -> tagService.getTagByName(tagName));
                break;

            case "deleteTag":
                assertThrows(ResourceNotFoundException.class, () -> tagService.deleteTag(tagService
                        .getTagByName(tagName).getId()));
                break;

            case "getTagById":
                assertThrows(ResourceNotFoundException.class, () -> tagService.getTagById(tagService
                        .getTagByName(tagName).getId()));
                break;
        }
    }

    @Test
    void testTagDelete_NotValidObject() {
        assertThrows(ResourceNotFoundException.class, () -> tagService.deleteTag((long) 100));
    }
}