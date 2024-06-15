package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    CharacterDto save(CharacterDataDto characterDataDto);

    CharacterDto findCharacterById(Long id);

    List<CharacterDto> findCharacterByNameContainingIgnoreCase(String searchString);
}
