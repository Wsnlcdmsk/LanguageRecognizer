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
    public String paigSatus()
    {
        return service.paigSatus();
    }

    @GetMapping("/{text}")
    public Language findNameOfLanguage(@PathVariable List<String> text){

        return service.findNameOfLanguage(text);
    }
}
