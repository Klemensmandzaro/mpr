package pl.edu.pjatk.zaj2.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class MyExceptonHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResorceNotExistException.class})
    protected ResponseEntity<Object> handleResorceNotExistException(ResorceNotExistException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IdentyfierAlreadyExistException.class})
    protected  ResponseEntity<Object> handleIdentifierAlreadyExistException(IdentyfierAlreadyExistException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {ChangeObjectWithNullValuesException.class})
    protected ResponseEntity<Object> handleChangeObjectWithNullValues(ChangeObjectWithNullValuesException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

}
