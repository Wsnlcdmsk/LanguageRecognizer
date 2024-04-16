package com.project.langrecognizer.controller;

import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.model.Language;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.project.langrecognizer.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/language")
@AllArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @Operation(summary = "Получение статуса сервера")
    @GetMapping
    @LoggingAnnotation
    public String pageStatus()
    {
        return languageService.pageStatus();
    }

    @Operation(summary = "Сохранение данных о языке")
    @PostMapping("/saveLanguage")
    @LoggingAnnotation
    public LanguageDTO saveLanguage(@Valid @RequestBody  Language language) {
        return languageService.saveLanguage(language);
    }

    @Operation(summary = "Сохранение данных о языках")
    @PostMapping("/saveLanguages")
    @LoggingAnnotation
    public List<LanguageDTO> saveLanguages(@Valid @RequestBody List<Language> languages) {
        return languageService.saveLanguages(languages);
    }

    @Operation(summary = "Просмотр данных всех языков")
    @GetMapping("/getLanguages")
    public List<Language> getLanguages() {
        return languageService.getLanguages();
    }

    @Operation(summary = "Просмотр данных о языке по id")
    @GetMapping("/getLanguageById/{id}")
    public Language getLanguageById( @PathVariable Long id) {
        return languageService.getLanguageById(id);
    }

    @Operation(summary = "Просмотр данных о языке по имени")
    @GetMapping("/getLanguageByName/{name}")
    public Language getLanguageByName( @PathVariable String name) {
        return languageService.getLanguageByName(name);
    }

    @Operation(summary = "Удаление данных о языке из базы данных")
    @DeleteMapping ("/delete/{id}")
    @LoggingAnnotation
    public String deleteLanguage(@Valid @PathVariable Long id) {
        return languageService.deleteLanguage(id);
    }

    @Operation(summary = "Обновление данных о языке")
    @PutMapping("/update")
    @LoggingAnnotation
    public LanguageDTO updateLanguage(@Valid @RequestBody LanguageDTO languageDTO) {
        return languageService.updateLanguage(languageDTO);
    }
}
