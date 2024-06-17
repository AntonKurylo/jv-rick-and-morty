package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CharactersResponseDataDto(
        CharacterInfoDto info,
        @JsonProperty("results")
        List<CharacterDataDto> characters
) {
}
