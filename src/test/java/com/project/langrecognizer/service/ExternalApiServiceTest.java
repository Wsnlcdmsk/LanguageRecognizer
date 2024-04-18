package com.project.langrecognizer.service;

import com.project.langrecognizer.model.Language;
import com.project.langrecognizer.model.Text;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ExternalApiServiceTest {

    @InjectMocks
    ExternalApiService service;
    String content = "Before becoming a Hollywood heartthrob and acclaimed actor"
            +", Brad Pitt made his television debut on the soap opera \"Another World\" in 1987."
            +"He played the role of Chris, a young man who came to town looking for his family.";

    @Test
    void testFindLanguageByExternalApi_Valid(){
        Language language = new Language();
        Text text = new Text();
        text.setId((long) 1);
        text.setContent(content);
        language.setId((long) 1);
        language.setTexts(List.of(text));
        text.setLanguage(service.detectLanguage(content));
        language.setName(service.detectLanguage(content).getName());
        language.setTexts(List.of(text));
        assertEquals("English", language.getName());
    }
}
