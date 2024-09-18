package com.ecom.ecom.advice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.ecom.exceptions.ErrorObject;
import com.ecom.ecom.exceptions.ResourceExists;
import com.ecom.ecom.exceptions.ResourceNotFound;

@RestControllerAdvice
public class Advice {

    @ExceptionHandler(ResourceExists.class)
    public ResponseEntity<ErrorObject> resourceExists(ResourceExists resourceExists) {
        ErrorObject errorObject = ErrorObject.builder().message(resourceExists.getMessage()).build();
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorObject> resourceNotFound(ResourceNotFound resourceExists) {
        ErrorObject errorObject = ErrorObject.builder().message(resourceExists.getMessage()).build();
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(io.jsonwebtoken.ExpiredJwtException .class)
    public ResponseEntity<ErrorObject> expiredToken(io.jsonwebtoken.ExpiredJwtException  resourceExists) {
        ErrorObject errorObject = ErrorObject.builder().message("expired token").build();
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(io.jsonwebtoken.MalformedJwtException .class)
    public ResponseEntity<ErrorObject> malformedJwtException(io.jsonwebtoken.MalformedJwtException  resourceExists) {
        ErrorObject errorObject = ErrorObject.builder().message("Invalid token").build();
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.FORBIDDEN);
    }




    @SuppressWarnings("deprecation")
    @ExceptionHandler(io.jsonwebtoken.SignatureException .class)
    public ResponseEntity<ErrorObject>signatureException(io.jsonwebtoken.SignatureException  resourceExists) {
        ErrorObject errorObject = ErrorObject.builder().message("Invalid token").build();
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.FORBIDDEN);
    }







    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorObject> missingData(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ObjectError> errors = methodArgumentNotValidException.getAllErrors();
        String message = "";
        for (ObjectError objectError : errors) {
            message = objectError.getDefaultMessage();
        }
        ErrorObject errorObject = ErrorObject.builder().message(message).build();
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }

}
