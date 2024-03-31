package com.project.langrecognizer.service;

import com.project.langrecognizer.model.Language;

public interface ExternalApiService {
    Language detectLanguage(String text);
}
