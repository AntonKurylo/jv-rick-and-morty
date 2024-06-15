package mate.academy.rickandmorty;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private final CharacterClient characterClient;
    private final CharacterService characterService;

    public Application(CharacterClient characterClient, CharacterService characterService) {
        this.characterClient = characterClient;
        this.characterService = characterService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            List<CharacterDataDto> characters = characterClient.getCharactersDataFromApi();
            characters.forEach(characterService::save);
        };
    }
}
