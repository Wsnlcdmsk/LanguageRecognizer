package com.project.langrecognizer.controller;

import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.model.Tag;
import com.project.langrecognizer.service.TagService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag")
@AllArgsConstructor
public class TagController {
    private TagService service;

    @PostMapping("/saveTag")
    public TagDTO saveTag(@Valid @RequestBody TagDTO tagDTO)
    {
        return service.saveTag(tagDTO);
    }

    @PostMapping("/saveTags")
    public List<TagDTO> saveTags(@Valid @RequestBody List<TagDTO> tagsDTO)
    {
        return service.saveTags(tagsDTO);
    }

    @GetMapping("/getTags")
    public List<Tag> getTags()
    {
        return service.getTags();
    }

    @GetMapping("/getTagById/{id}")
    public Tag getTagById(@Valid @PathVariable Long id)
    {
        return service.getTagById(id);
    }

    @GetMapping("/getTagByContent/{name}")
    public List<Tag> getTagByName(@Valid @PathVariable String name)
    {
        return service.getTagByName(name);
    }

    @DeleteMapping("/deleteTag/{id}")
    public String deleteTag(@Valid @PathVariable Long id)
    {
        return service.deleteTag(id);
    }

    @PutMapping("/update")
    public TagDTO updateTag(@Valid @RequestBody TagDTO tagDTO)
    {
        return service.updateTag(tagDTO);
    }
}
