package com.tensquare.spit.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e) {
        System.out.println("========================ERROR===========================");
        e.printStackTrace();
        System.out.println("========================================================");
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }

}