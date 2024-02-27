package com.project.langrecognizer.service.imp;

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
        return new Language(text, "English");
    }
}
