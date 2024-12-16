package pl.edu.pjatk.zaj2.exception;

public class ResorceNotExistException extends RuntimeException {
    public ResorceNotExistException() {
        super("Podany zasób nie istnieje");
    }
}
