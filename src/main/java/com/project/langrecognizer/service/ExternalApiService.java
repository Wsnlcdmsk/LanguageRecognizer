/**
 * The {@code service} package contains the {@link ExternalApiService}
 * which provides functionality to interact
 * with an external language detection API.
 * It sends a request with text input to the API
 * and retrieves the detected language.
 */
package com.project.langrecognizer.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.model.Language;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@AllArgsConstructor
public class ExternalApiService {

    /** The URL of the external API that will be used
     * to detect the language of a given text. */
    private static final String API_URL =
            "https://api.apyhub.com/detect/language";

    /**
     * Detects the language of the given text using an external API.
     * @param text The text for which the language
     * needs to be detected.
     * @return The detected language.
     * @throws BadRequestException If no text content is provided
     * or if the request is interrupted.
     */
    @LoggingAnnotation
    public Language detectLanguage(final String text) {
        if (text == null) {
            throw new BadRequestException("No content provided");
        }
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .POST(HttpRequest.BodyPublishers.
                        ofString("{\"text\": \"" + text + "\"}"))
                .setHeader("Content-Type", "application/json")
                .setHeader("apy-token",
                        "APY0gCUDWWVG4oiF0p9D5Slbo4Ireht1hypbraa"
                                + "WggXoDyH7V5RjLlVUhdK0nDKH0A1nlI3")
                .build();
            HttpResponse<String> response = client.
                    send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.
                    body()).get("data");
            String detectedLanguage = jsonNode.get("language").asText();
            Language language = new Language();
            language.setName(detectedLanguage);
            return language;
    }
}
