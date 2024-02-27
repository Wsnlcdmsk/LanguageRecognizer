package com.project.langrecognizer.service.Impl;

import com.project.langrecognizer.model.Language;
import com.project.langrecognizer.service.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryLanguageService implements LanguageService {
    @Override
    public String paigSatus()
    {
        return "Server is working";
    }

    public Language findNameOfLanguage(List<String> text){
        Language language = new Language(text, "English");
        return language;
    }
}
