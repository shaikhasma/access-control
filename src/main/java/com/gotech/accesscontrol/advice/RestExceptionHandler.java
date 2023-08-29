package com.gotech.accesscontrol.advice;

import com.gotech.accesscontrol.error.ErrorMessage;
import com.gotech.accesscontrol.exception.InvalidUserException;
import com.gotech.accesscontrol.exception.UserAlreadyExistException;
import com.gotech.accesscontrol.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMessage(exception.getMessage());
        errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorMessage, errorMessage.getStatusCode());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleUserAlreadyExistsException(
            UserAlreadyExistException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMessage(exception.getMessage());
        errorMessage.setStatusCode(HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorMessage, errorMessage.getStatusCode());
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorMessage> handleInvalidUserException(InvalidUserException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMessage(exception.getMessage());
        errorMessage.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(errorMessage, errorMessage.getStatusCode());
    }
}
