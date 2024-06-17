package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoints for managing characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get random character",
            description = "Returns a character obtained by a random id")
    public CharacterDto findCharacterById() {
        return characterService.findById();
    }

    @GetMapping("/name")
    @Operation(summary = "Search for characters by part of the name",
            description = "Returns a list of all characters whose name contains the search string")
    public List<CharacterDto> searchCharacterByName(
            @RequestParam(defaultValue = "rick") String searchString) {
        return characterService.searchByName(searchString.trim());
    }
}
