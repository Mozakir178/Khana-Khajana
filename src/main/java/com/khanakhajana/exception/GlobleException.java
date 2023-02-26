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

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(BillException.class)
    public ResponseEntity<MyErrorDetails> userExceptionHandler(BillException bill, WebRequest req){

        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),bill.getMessage(),req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<MyErrorDetails> userExceptionHandler(CategoryException categoryException, WebRequest req){

        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),categoryException.getMessage(),req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(FoodCartException.class)
    public ResponseEntity<MyErrorDetails> userExceptionHandler(FoodCartException foodCartException, WebRequest req){

        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),foodCartException.getMessage(),req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<MyErrorDetails> userExceptionHandler(ItemException itemException, WebRequest req){

        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),itemException.getMessage(),req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(OrderDetailException.class)
    public ResponseEntity<MyErrorDetails> userExceptionHandler(OrderDetailException orderDetailException, WebRequest req){

        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),orderDetailException.getMessage(),req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(RestaurantException.class)
    public ResponseEntity<MyErrorDetails> userExceptionHandler(RestaurantException restaurantException, WebRequest req){

        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),restaurantException.getMessage(),req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

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
