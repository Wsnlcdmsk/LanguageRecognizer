package com.project.langrecognizer.controller;

import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.model.Tag;
import com.project.langrecognizer.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag")
@AllArgsConstructor
public class TagController {
    private TagService service;

    @Operation(summary = "Сохранение данных о тэге")
    @PostMapping("/saveTag")
    @LoggingAnnotation
    public TagDTO saveTag(@Valid @RequestBody Tag tag)
    {
        return service.saveTag(tag);
    }

    @Operation(summary = "Сохранение данных о тэгах")
    @PostMapping("/saveTags")
    @LoggingAnnotation
    public List<TagDTO> saveTags(@Valid @RequestBody List<Tag> tags)
    {
        return service.saveTags(tags);
    }

    @Operation(summary = "Просмотр данных всех тэгов")
    @GetMapping("/getTags")
    public List<Tag> getTags()
    {
        return service.getTags();
    }

    @Operation(summary = "Просмотр данных о тэге по id")
    @GetMapping("/getTagById/{id}")
    public Tag getTagById(@PathVariable Long id)
    {
        return service.getTagById(id);
    }

    @Operation(summary = "Просмотр данных о тэге по имени")
    @GetMapping("/getTagByContent/{name}")
    public Tag getTagByName(@PathVariable String name)
    {
        return service.getTagByName(name);
    }

    @Operation(summary = "Удаление данных о тэге из базы данных")
    @DeleteMapping("/deleteTag/{id}")
    @LoggingAnnotation
    public String deleteTag(@PathVariable Long id)
    {
        return service.deleteTag(id);
    }

    @Operation(summary = "Обновление данных о тэге")
    @PutMapping("/update")
    @LoggingAnnotation
    public TagDTO updateTag(@Valid @RequestBody TagDTO tagDTO)
    {
        return service.updateTag(tagDTO);
    }
}
