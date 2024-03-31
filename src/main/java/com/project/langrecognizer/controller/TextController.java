package com.project.langrecognizer.controller;

import com.project.langrecognizer.model.Text;
import com.project.langrecognizer.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/text")
@AllArgsConstructor
public class TextController {
    private TextService service;

    @PostMapping("/saveText")
    public Text saveText(@RequestBody Text text)
    {
        return service.saveText(text);
    }

    @PostMapping("/saveTexts")
    public List<Text> saveTexts(@RequestBody List<Text> texts)
    {
        return service.saveTexts(texts);
    }

    @GetMapping("/getTexts")
    public List<Text> getTexts()
    {
        return service.getTexts();
    }

    @GetMapping("/getTextById/{id}")
    public Text getTextById(@PathVariable Long id)
    {
        return service.getTextById(id);
    }

    @GetMapping("/getTextByContent/{content}")
    public Text getTextByContent(@PathVariable String content)
    {
        return service.getTextByContent(content);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteText(@PathVariable Long id)
    {
        return service.deleteText(id);
    }

    @PutMapping("/update")
    public Text updateText(@RequestBody Text text)
    {
        return service.updateText(text);
    }
}
