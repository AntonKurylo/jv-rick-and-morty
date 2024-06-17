package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static int CHARACTER_COUNT = 0;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    @Override
    public CharacterDto save(CharacterDataDto characterDataDto) {
        CHARACTER_COUNT++;
        return characterMapper.toDto(
                characterRepository.save(
                        characterMapper.toModel(characterDataDto)));
    }

    @Override
    public CharacterDto findById() {
        long id = random.nextLong(CHARACTER_COUNT) + 1;
        Character characterRM = characterRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't find a character by id: " + id));
        return characterMapper.toDto(characterRM);
    }

    @Override
    public List<CharacterDto> searchByName(String searchString) {
        return characterRepository.findCharacterByNameContainingIgnoreCase(searchString).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
