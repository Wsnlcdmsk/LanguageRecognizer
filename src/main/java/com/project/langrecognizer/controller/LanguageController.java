package com.project.LangRecognizer.controller;

import com.project.LangRecognizer.model.Language;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.project.LangRecognizer.service.LanguageService;

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

    @GetMapping("/{Text}")
    public Language findNameOfLanguage(@PathVariable List<String> Text){

        return service.findNameOfLanguage(Text);
    }
}
