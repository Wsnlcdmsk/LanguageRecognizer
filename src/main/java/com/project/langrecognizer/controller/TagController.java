/**
 * Контроллер для управления данными о тэгах.
 */
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
@CrossOrigin
public class TagController {
    /** The service for performing operations
     * related to tags. */
    private TagService service;


    /**
     * Сохраняет данные о тэге.
     * @param tag Данные о тэге для сохранения.
     * @return Сохраненные данные о тэге в формате DTO.
     */
    @Operation(summary = "Сохранение данных о тэге")
    @PostMapping("/saveTag")
    @LoggingAnnotation
    public TagDTO saveTag(@Valid @RequestBody final Tag tag) {
        return service.saveTag(tag);
    }

    /**
     * Сохраняет данные о тэгах.
     * @param tags Список данных о тэгах для сохранения.
     * @return Сохраненные данные о тэгах в формате DTO.
     */
    @Operation(summary = "Сохранение данных о тэгах")
    @PostMapping("/saveTags")
    @LoggingAnnotation
    public List<TagDTO> saveTags(@Valid @RequestBody final List<Tag> tags) {
        return service.saveTags(tags);
    }

    /**
     * Возвращает данные всех тэгов.
     * @return Данные всех тэгов в формате DTO.
     */
    @Operation(summary = "Просмотр данных всех тэгов")
    @GetMapping("/getTags")
    public List<TagDTO> getTags() {
        return service.getTags();
    }

    /**
     * Возвращает данные о тэге по его идентификатору.
     * @param id Идентификатор тэга.
     * @return Данные о тэге в формате DTO.
     */
    @Operation(summary = "Просмотр данных о тэге по id")
    @GetMapping("/getTagById/{id}")
    public TagDTO getTagById(@PathVariable final Long id) {
        return service.getTagById(id);
    }

    /**
     * Возвращает данные о тэге по его имени.
     * @param name Имя тэга.
     * @return Данные о тэге в формате DTO.
     */
    @Operation(summary = "Просмотр данных о тэге по имени")
    @GetMapping("/getTagByContent/{name}")
    public TagDTO getTagByName(@PathVariable final String name) {
        return service.getTagByName(name);
    }

    /**
     * Удаляет данные о тэге по его идентификатору.
     * @param id Идентификатор тэга.
     * @return Сообщение об успешном удалении или ошибке.
     */
    @Operation(summary = "Удаление данных о тэге из базы данных")
    @DeleteMapping("/deleteTag/{id}")
    @LoggingAnnotation
    public String deleteTag(@PathVariable final Long id) {
        return service.deleteTag(id);
    }

    /**
     * Обновляет данные о тэге.
     * @param tag Обновленные данные о тэге.
     * @return Обновленные данные о тэге в формате DTO.
     */
    @Operation(summary = "Обновление данных о тэге")
    @PutMapping("/update")
    @LoggingAnnotation
    public TagDTO updateTag(@Valid @RequestBody final Tag tag) {
        return service.updateTag(tag);
    }
}
