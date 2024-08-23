package gianlucamessina.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String isbn) {
        super("Il record con codice ISBN: " + isbn + " non è stato trovato");
    }
}
