package com.project.langrecognizer.controller;

import com.project.langrecognizer.model.Language;
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

    @GetMapping
    public String pageStatus()
    {
        return languageService.pageStatus();
    }

   @PostMapping("/saveLanguage")
    public Language saveLanguage(@Valid @RequestBody  Language language) {
        return languageService.saveLanguage(language);
    }

    @PostMapping("/saveLanguages")
    public List<Language> saveLanguages(@Valid @RequestBody List<Language> languages) {
        return languageService.saveLanguages(languages);
    }

    @GetMapping("/getLanguages")
    public List<Language> getLanguages() {
        return languageService.getLanguages();
    }

    @GetMapping("/getLanguageById/{id}")
    public Language getLanguageById(@Valid @PathVariable Long id) {
        return languageService.getLanguageById(id);
    }

    @GetMapping("/getLanguageByName/{name}")
    public List<Language> getLanguageByName(@Valid @PathVariable String name) {
        return languageService.getLanguageByName(name);
    }

    @DeleteMapping ("/delete/{id}")
    public String deleteLanguage(@Valid @PathVariable Long id) {
        return languageService.deleteLanguage(id);
    }

    @PutMapping("/update")
    public Language updateLanguage(@Valid @RequestBody Language language) {
        return languageService.updateLanguage(language);
    }
}
