package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.model.Tag;

import java.util.List;

public interface TagService {
    TagDTO saveTag(TagDTO tagDTO);
    List<TagDTO> saveTags(List<TagDTO> tagsDTO);
    List<Tag> getTags();
    Tag getTagById(Long id);
    List<Tag> getTagByName(String name);
    String deleteTag(Long id);
    TagDTO updateTag(TagDTO tagDTO);
}
