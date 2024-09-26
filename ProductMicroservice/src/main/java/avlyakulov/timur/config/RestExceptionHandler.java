package avlyakulov.timur.config;

import avlyakulov.timur.exception.AppException;
import avlyakulov.timur.exception.AppMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    @ResponseStatus
    public ResponseEntity<AppMessage> handleException(AppException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new AppMessage(new Date(), ex.getMessage()));
    }
}
