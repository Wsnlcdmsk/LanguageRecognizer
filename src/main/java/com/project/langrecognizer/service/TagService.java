package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.model.Tag;

import java.util.List;

public interface TagService {
    TagDTO saveTag(Tag tag);
    List<TagDTO> saveTags(List<Tag> tags);
    List<TagDTO> getTags();
    TagDTO getTagById(Long id);
    TagDTO getTagByName(String name);
    String deleteTag(Long id);
    TagDTO updateTag(Tag tag);
}
