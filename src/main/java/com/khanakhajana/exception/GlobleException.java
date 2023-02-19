package com.khanakhajana.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobleException {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MyErrorDetails> userExceptionHandler(UserException user, WebRequest req){

        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),user.getMessage(),req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.NO_CONTENT);

    }

    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<MyErrorDetails> userLoginExceptionHandler(UserLoginException user, WebRequest req){

        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),user.getMessage(),req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> genralExceptionHandler(Exception user, WebRequest req){

        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),user.getMessage(),req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorDetails> noExceptionHandler(NoHandlerFoundException user, WebRequest req){

        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),user.getMessage(),req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_GATEWAY);

    }



}
