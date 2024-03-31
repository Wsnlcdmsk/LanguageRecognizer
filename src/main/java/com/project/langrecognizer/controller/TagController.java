package com.project.langrecognizer.controller;

import com.project.langrecognizer.model.Tag;
import com.project.langrecognizer.service.TagService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag")
@AllArgsConstructor
public class TagController {
    private TagService service;

    @PostMapping("/saveTag")
    public Tag saveTag(@Valid @RequestBody Tag tag)
    {
        return service.saveTag(tag);
    }

    @PostMapping("/saveTags")
    public List<Tag> saveTags(@Valid @RequestBody List<Tag> tags)
    {
        return service.saveTags(tags);
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
    public Tag updateTag(@Valid @RequestBody Tag tag)
    {
        return service.updateTag(tag);
    }
}
