package com.project.langrecognizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения LangRecognizer.
 * Запускает Spring Boot приложение.
 */
@SpringBootApplication
public class LangRecognizerApplication {
    public static void main(final String[] args) {
        SpringApplication.run(LangRecognizerApplication.class, args);
    }
}
