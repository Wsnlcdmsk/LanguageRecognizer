package com.project.langrecognizer.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Configuration class for the Language Recognizer API.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Language Recognizer API",
                description = "An API for text language recognition.",
                version = "1.0",
                contact = @Contact(
                        name = "Timofey Petrovich",
                        email = "pertovtim@gmail.com"
                )
        )
)
public class LangRecognizerConfig {
}
