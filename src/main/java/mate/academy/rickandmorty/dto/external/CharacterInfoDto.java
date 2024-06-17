package mate.academy.rickandmorty.dto.external;

public record CharacterInfoDto(
        int count,
        int pages,
        String next,
        String prev
) {
}
