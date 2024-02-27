package com.project.LangRecognizer.service.Impl;

import com.project.LangRecognizer.model.Language;
import com.project.LangRecognizer.service.LanguageService;
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

    public Language findNameOfLanguage(List<String> Text){
        Language language = new Language(Text, "English");
        return language;
    }
}
