/**
 * Контроллер для управления данными о языках.
 */
package com.project.langrecognizer.controller;

import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.dto.LanguageDTO;
import com.project.langrecognizer.model.Language;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.project.langrecognizer.service.LanguageService;

import java.util.List;

/**
 * Controller class for managing language-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/language")
@AllArgsConstructor
public class LanguageController {

    /** The service for performing operations
     * related to languages. */
    private final LanguageService languageService;


    /**
     * Endpoint to retrieve the server status.
     * @return The server status.
     */
    @Operation(summary = "Retrieve server status")
    @GetMapping
    @LoggingAnnotation
    public String pageStatus() {
        return languageService.pageStatus();
    }

    /**
     * Endpoint to save language data.
     * @param language The language data to save.
     * @return The saved language data.
     */
    @Operation(summary = "Save language data")
    @PostMapping("/saveLanguage")
    @LoggingAnnotation
    public LanguageDTO saveLanguage(
            @Valid @RequestBody final  Language language) {
        return languageService.saveLanguage(language);
    }

    /**
     * Endpoint to save multiple language data.
     * @param languages The list of language data to save.
     * @return The list of saved language data.
     */
    @Operation(summary = "Save multiple language data")
    @PostMapping("/saveLanguages")
    @LoggingAnnotation
    public List<LanguageDTO> saveLanguages(
            @Valid @RequestBody final  List<Language> languages) {
        return languageService.saveLanguages(languages);
    }

    /**
     * Endpoint to retrieve data of all languages.
     * @return The list of all language data.
     */
    @Operation(summary = "Retrieve data of all languages")
    @GetMapping("/getLanguages")
    public List<LanguageDTO> getLanguages() {
        return languageService.getLanguages();
    }

    /**
     * Endpoint to retrieve language data by id.
     * @param id The id of the language.
     * @return The language data corresponding to the id.
     */
    @Operation(summary = "Retrieve language data by id")
    @GetMapping("/getLanguageById/{id}")
    public LanguageDTO getLanguageById(@PathVariable final Long id) {
        return languageService.getLanguageById(id);
    }

    /**
     * Endpoint to retrieve language data by name.
     * @param name The name of the language.
     * @return The language data corresponding to the name.
     */
    @Operation(summary = "Retrieve language data by name")
    @GetMapping("/getLanguageByName/{name}")
    public LanguageDTO getLanguageByName(@PathVariable final String name) {
        return languageService.getLanguageByName(name);
    }

    /**
     * Endpoint to delete language data by id.
     * @param id The id of the language to delete.
     * @return A message indicating the success or failure of the operation.
     */
    @Operation(summary = "Delete language data by id")
    @DeleteMapping("/delete/{id}")
    @LoggingAnnotation
    public String deleteLanguage(@PathVariable final Long id) {
        return languageService.deleteLanguage(id);
    }

    /**
     * Endpoint to update language data.
     * @param language The language data to update.
     * @return The updated language data.
     */
    @Operation(summary = "Update language data")
    @PutMapping("/update")
    @LoggingAnnotation
    public LanguageDTO updateLanguage(
            @Valid @RequestBody final Language language) {
        return languageService.updateLanguage(language);
    }
}
