package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.external.CharactersResponseDataDto;
import mate.academy.rickandmorty.exception.CharacterExternalLoadException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    private static final String URL = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    public List<CharacterDataDto> getCharactersDataFromApi() {
        List<CharacterDataDto> charactersDataDto = new ArrayList<>();
        String currentUrl = URL;
        while (currentUrl != null) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(currentUrl))
                        .build();

                HttpResponse<String> response = httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

                CharactersResponseDataDto dataDto = objectMapper.readValue(
                        response.body(),
                        CharactersResponseDataDto.class
                );
                charactersDataDto.addAll(dataDto.characters());
                currentUrl = dataDto.info().next();
            } catch (IOException | InterruptedException e) {
                throw new CharacterExternalLoadException("Can't load character from API", e);
            }
        }
        return charactersDataDto;
    }
}
