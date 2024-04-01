package com.project.langrecognizer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.langrecognizer.model.Language;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@AllArgsConstructor
public class ExternalApiService{

    private static final String API_URL = "https://api.apyhub.com/detect/language";
    private static final String API_TOKEN = "APY0gCUDWWVG4oiF0p9D5Slbo4Ireht1hypbraaWggXoDyH7V5RjLlVUhdK0nDKH0A1nlI3";

    public Language detectLanguage(String text) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .POST(HttpRequest.BodyPublishers.ofString("{\"text\": \"" + text + "\"}"))
                .setHeader("Content-Type", "application/json")
                .setHeader("apy-token", API_TOKEN)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body()).get("data");
            if(jsonNode == null)
            {
                throw new Exception () ;
            }
            String detectedLanguage = jsonNode.get("language").asText();
            Language language = new Language();
            language.setName(detectedLanguage);
            return language;
        } catch (Exception e) {
            e.printStackTrace();
            Language language = new Language();
            language.setName("An error occurred");
            return language;
        }
    }
}


