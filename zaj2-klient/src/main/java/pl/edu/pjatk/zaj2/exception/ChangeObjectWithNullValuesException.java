package pl.edu.pjatk.zaj2.exception;

public class ChangeObjectWithNullValuesException extends RuntimeException {
    public ChangeObjectWithNullValuesException() {
        super("Nie mozesz zaktualizowac przedmiotu pustymi polami");
    }
}
