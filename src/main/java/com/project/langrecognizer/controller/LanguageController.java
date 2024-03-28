package com.project.langrecognizer.controller;

import com.project.langrecognizer.model.Language;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.project.langrecognizer.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/language")
@AllArgsConstructor
public class LanguageController {

    private final LanguageService service;

    @GetMapping
    public String pageStatus()
    {
        return service.pageStatus();
    }

   @PostMapping("/saveLanguage")
    public Language saveLanguage(@RequestBody Language language) {
        return service.saveLanguage(language);
    }

    @PostMapping("/saveLanguages")
    public List<Language> saveLanguages(@RequestBody List<Language> languages) {
        return service.saveLanguages(languages);
    }

    @GetMapping("/getLanguages")
    public List<Language> getLanguages() {
        return service.getLanguages();
    }

    @GetMapping("/getLanguageById/{id}")
    public Language getLanguageById(@PathVariable Long id) {
        return service.getLanguageById(id);
    }

    @GetMapping("/getLanguageByName/{name}")
    public List<Language> getLanguageByName(@PathVariable String name) {
        return service.getLanguageByName(name);
    }

    @DeleteMapping ("/delete/{id}")
    public String deleteLanguage(@PathVariable Long id) {
        return service.deleteLanguage(id);
    }

    @PutMapping("/update")
    public Language updateLanguage(@RequestBody Language language) {
        return service.updateLanguage(language);
    }
}
