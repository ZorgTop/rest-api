package com.appsdeveloperblog.app.ws.mobileappws.exceptions;

import com.appsdeveloperblog.app.ws.mobileappws.ui.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<?> handleUserServiceException(UserServiceException ex) {
        ErrorMessage errorMessages = new ErrorMessage(ex.getMessage(), new Date());
        return new ResponseEntity<>(errorMessages, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleOtherException(Exception ex) {
        ErrorMessage errorMessages = new ErrorMessage(ex.getMessage(), new Date());
        return new ResponseEntity<>(errorMessages, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

