package com.project.langrecognizer.service;

import com.project.langrecognizer.exception.BadRequestException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExternalApiServiceTest {

    @InjectMocks
    ExternalApiService service;
    String content = "Before becoming a Hollywood heartthrob and acclaimed actor"
            +", Brad Pitt made his television debut on the soap opera \"Another World\" in 1987."
            +"He played the role of Chris, a young man who came to town looking for his family.";

    @Test
    void testFindLanguageByExternalApi_NoValidObject(){
        String emptyContent = new String();
        assertThrows(BadRequestException.class, () -> service.detectLanguage(emptyContent));
    }
}
