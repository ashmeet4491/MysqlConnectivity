package com.example.demomysql;

import com.example.demomysql.utils.BadPersonRequestExceptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ControllerAdvice
public class HandlerConfig
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(HttpServletRequest httpServletRequest, MethodArgumentNotValidException ex)
    {
        //logic
        BindingResult result=ex.getBindingResult();//catching the errors
        List<FieldError> fieldErrors=result.getFieldErrors();


        List<String> errors= fieldErrors.stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadPersonRequestExceptions.class)
    public  ResponseEntity handleBadRequestException(BadPersonRequestExceptions ex)
    {
        String msg=ex.getMessage();
        return  new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
    }

}
