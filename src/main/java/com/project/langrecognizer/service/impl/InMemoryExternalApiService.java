package com.project.langrecognizer.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.langrecognizer.model.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.project.langrecognizer.service.ExternalApiService;

@Service
@AllArgsConstructor
public class InMemoryExternalApiService implements ExternalApiService {

    private static final String API_URL = "https://api.apyhub.com/detect/language";
    private static final String API_TOKEN = "APY0gCUDWWVG4oiF0p9D5Slbo4Ireht1hypbraaWggXoDyH7V5RjLlVUhdK0nDKH0A1nlI3";

    public Language detectLanguage(String text) {
        RestTemplate restTemplate = new RestTemplate();
        Language language = new Language();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_TOKEN);
        String requestBody = "{\"text\": \"" + text + "\"}";
        String response = restTemplate.postForObject(API_URL, requestBody, String.class);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            String detectedLanguage = jsonNode.get("language").asText();
            language.setName(detectedLanguage);
            return language;
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
            language.setName("An error occurred");
            return new Language();
        }
    }

}


