package com.licslan.searchserver.globalexception;


import com.licslan.searchserver.response.ResView;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResView handle(BindException e) {

        List<FieldError> fieldErrorList = e.getFieldErrors();

        ResView view = ResView.failed(0, "参数不正确！");

        for (FieldError fieldError : fieldErrorList) {
            view.add(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return view;

    }


}
