package com.project.langrecognizer.controller;

import com.project.langrecognizer.model.Language;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.project.langrecognizer.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/languages")
@AllArgsConstructor
public class LanguageController {

    private final LanguageService service;

    @GetMapping
    public String pageStatus()
    {
        return service.pageStatus();
    }

    @GetMapping("/{text}")
    public Language findNameOfLanguage(@PathVariable String text){

        return service.findNameOfLanguage(text);
    }

  /* @PostMapping("/saveLanguage")
    public Language saveLanguage(Language language) {
        return service.saveLanguage(language);
    }

    @PostMapping("/saveLanguages")
    public List<Language> saveLanguages(List<Language> languages) {
        return service.saveLanguages(languages);
    }

    @GetMapping("/getLanguages")
    public List<Language> getLanguages() {
        return service.getLanguages();
    }

    @GetMapping("/language/{id}")
    public Language getLanguageById(@PathVariable int id) {
        return service.getLanguageById(id);
    }

    @GetMapping("/language/{name}")
    public Language getLanguageByName(@PathVariable String name) {
        return service.getLanguageByName(name);
    }

    @DeleteMapping ("/delete/{id}")
    public String deleteLanguage(@PathVariable int id) {
        return service.deleteLanguage(id);
    }

    @PutMapping("/update")
    public Language updateLanguage(@RequestBody Language language) {
        return service.updateLanguage(language);
    }*/
}
