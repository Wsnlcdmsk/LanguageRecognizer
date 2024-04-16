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

    @Operation(summary = "Сохранение данных о тексте")
    @PostMapping("/saveText")
    @LoggingAnnotation
    public TextDTO saveText(@Valid @RequestBody Text text)
    {
        return service.saveText(text);
    }

    @Operation(summary = "Сохранение данных о текстах")
    @PostMapping("/saveTexts")
    public List<TextDTO> saveTexts(@Valid @RequestBody List<Text> texts)
    {
        return service.saveTexts(texts);
    }

    @Operation(summary = "Просмотр данных всех текстов")
    @GetMapping("/getTexts")
    public List<Text> getTexts()
    {
        return service.getTexts();
    }

    @Operation(summary = "Просмотр данных о тексте по id")
    @GetMapping("/getTextById/{id}")
    public Text getTextById(@PathVariable Long id)
    {
        return service.getTextById(id);
    }

    @Operation(summary = "Просмотр данных о тексте по содержанию")
    @GetMapping("/getTextByContent/{content}")
    public Text getTextByContent(@PathVariable String content)
    {
        return service.getTextByContent(content);
    }

    @Operation(summary = "Удаление данных о тексте из базы данных")
    @DeleteMapping("/delete/{id}")
    @LoggingAnnotation
    public String deleteText(@PathVariable Long id)
    {
        return service.deleteText(id);
    }

    @Operation(summary = "Обновление данных о тексте")
    @PutMapping("/update")
    @LoggingAnnotation
    public TextDTO updateText(@Valid @RequestBody TextDTO textDTO)
    {
        return service.updateText(textDTO);
    }

    @Operation(summary = "Получение отсортированных данных о тексте с определенным тэгом")
    @GetMapping("/findTextSortedByTag/{tag}")
    @LoggingAnnotation
    public List<String> getTextsSortedByTag(@PathVariable("tag") String tag){
        List<String> texts = service.findTextsSortedByTag(tag);
        return texts;
    }

    @Operation(summary = "Получение отсортированных данных о тексте с определенным языком")
    @GetMapping("/findTextSortedByLanguage/{language}")
    public List<String> getTextsSortedByLanguage(@PathVariable("language") String language){
        List<String> texts = service.findTextsSortedByLanguage(language);
        return texts;
    }
}
