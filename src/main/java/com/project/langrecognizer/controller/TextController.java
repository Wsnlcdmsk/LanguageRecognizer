/**
 * Контроллер для управления данными о текстах.
 */
package com.project.langrecognizer.controller;

import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.model.Text;
import com.project.langrecognizer.service.TextService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/text")
@AllArgsConstructor
public class TextController {
    private TextService service;

    /**
     * Сохраняет данные о тексте.
     *
     * @param text Данные о тексте для сохранения.
     * @return Сохраненные данные о тексте в формате DTO.
     */
    @Operation(summary = "Сохранение данных о тексте")
    @PostMapping("/saveText")
    @LoggingAnnotation
    public TextDTO saveText(@Valid @RequestBody Text text) {
        return service.saveText(text);
    }

    /**
     * Сохраняет данные о текстах.
     *
     * @param texts Список данных о текстах для сохранения.
     * @return Сохраненные данные о текстах в формате DTO.
     */
    @Operation(summary = "Сохранение данных о текстах")
    @PostMapping("/saveTexts")
    public List<TextDTO> saveTexts(@Valid @RequestBody List<Text> texts) {
        return service.saveTexts(texts);
    }

    /**
     * Возвращает данные всех текстов.
     *
     * @return Данные всех текстов в формате DTO.
     */
    @Operation(summary = "Просмотр данных всех текстов")
    @GetMapping("/getTexts")
    public List<TextDTO> getTexts() {
        return service.getTexts();
    }

    /**
     * Возвращает данные о тексте по его идентификатору.
     *
     * @param id Идентификатор текста.
     * @return Данные о тексте в формате DTO.
     */
    @Operation(summary = "Просмотр данных о тексте по id")
    @GetMapping("/getTextById/{id}")
    public TextDTO getTextById(@PathVariable Long id) {
        return service.getTextById(id);
    }

    /**
     * Возвращает данные о тексте по его содержимому.
     *
     * @param content Содержимое текста.
     * @return Данные о тексте в формате DTO.
     */
    @Operation(summary = "Просмотр данных о тексте по содержанию")
    @GetMapping("/getTextByContent/{content}")
    public TextDTO getTextByContent(@PathVariable String content) {
        return service.getTextByContent(content);
    }

    /**
     * Удаляет данные о тексте по его идентификатору.
     *
     * @param id Идентификатор текста.
     * @return Сообщение об успешном удалении или ошибке.
     */
    @Operation(summary = "Удаление данных о тексте из базы данных")
    @DeleteMapping("/delete/{id}")
    @LoggingAnnotation
    public String deleteText(@PathVariable Long id) {
        return service.deleteText(id);
    }

    /**
     * Обновляет данные о тексте.
     *
     * @param text Обновленные данные о тексте.
     * @return Обновленные данные о тексте в формате DTO.
     */
    @Operation(summary = "Обновление данных о тексте")
    @PutMapping("/update")
    @LoggingAnnotation
    public TextDTO updateText(@RequestBody Text text) {
        return service.updateText(text);
    }

    /**
     * Возвращает список текстов отсортированных по указанному тегу.
     *
     * @param tag Тег, по которому нужно отсортировать тексты.
     * @return Список отсортированных текстов.
     */
    @Operation(summary = "Получение отсортированных данных о тексте с определенным тэгом")
    @GetMapping("/findTextSortedByTag/{tag}")
    @LoggingAnnotation
    public List<String> getTextsSortedByTag(@PathVariable("tag") String tag) {
        List<String> texts = service.findTextsSortedByTag(tag);
        return texts;
    }

    /**
     * Возвращает список текстов отсортированных по указанному языку.
     *
     * @param language Язык, по которому нужно отсортировать тексты.
     * @return Список отсортированных текстов.
     */
    @Operation(summary = "Получение отсортированных данных о тексте с определенным языком")
    @GetMapping("/findTextSortedByLanguage/{language}")
    public List<String> getTextsSortedByLanguage(@PathVariable("language") String language) {
        List<String> texts = service.findTextsSortedByLanguage(language);
        return texts;
    }
}
