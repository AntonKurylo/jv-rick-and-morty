package mate.academy.rickandmorty.exception;

public class CharacterExternalLoadException extends RuntimeException {
    public CharacterExternalLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
