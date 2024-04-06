package com.project.langrecognizer.controller;

import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.model.Text;
import com.project.langrecognizer.service.TextService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/text")
@AllArgsConstructor
public class TextController {
    private TextService service;

    @PostMapping("/saveText")
    public TextDTO saveText(@RequestBody TextDTO textDTO)
    {
        return service.saveText(textDTO);
    }

    @PostMapping("/saveTexts")
    public List<TextDTO> saveTexts(@RequestBody List<TextDTO> textsDTO)
    {
        return service.saveTexts(textsDTO);
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
    public TextDTO updateText(@RequestBody TextDTO textDTO)
    {
        return service.updateText(textDTO);
    }

    @GetMapping("/findTextSortedByTag/{tag}")
    public List<String> getTextsSortedByTag(@PathVariable("tag") String tag){
        List<String> texts = service.findTextsSortedByTag(tag);
        return texts;
    }
}
