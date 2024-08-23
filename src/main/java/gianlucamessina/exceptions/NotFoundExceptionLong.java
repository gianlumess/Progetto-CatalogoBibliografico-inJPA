package gianlucamessina.exceptions;

public class NotFoundExceptionLong extends RuntimeException {
    public NotFoundExceptionLong(long id) {
        super("L'utente con tessera_ID= " + id + " non è stato trovato");
    }
}
