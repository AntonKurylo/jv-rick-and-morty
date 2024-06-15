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
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character?page=%d";
    private static final int PAGE_COUNT = 42;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    private final ObjectMapper objectMapper;

    public List<CharacterDataDto> getCharactersDataFromApi() {
        List<CharacterDataDto> charactersDataDto = new ArrayList<>();
        for (int i = 1; i <= PAGE_COUNT; i++) {
            String url = BASE_URL.formatted(i);

            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
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
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return charactersDataDto;
    }
}
