package pl.edu.pjatk.zaj2.exception;

public class IdentyfierAlreadyExistException extends RuntimeException {
    public IdentyfierAlreadyExistException() {
        super("Zwierze o podanym identyfikatorze juz istnieje");
    }
}
