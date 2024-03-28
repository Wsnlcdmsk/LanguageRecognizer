package com.project.langrecognizer.service;

import com.project.langrecognizer.model.Tag;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);
    List<Tag> saveTags(List<Tag> tags);
    List<Tag> getTags();
    Tag getTagById(Long id);
    List<Tag> getTagByName(String name);
    String deleteTag(Long id);
    Tag updateTag(Tag tag);
}
