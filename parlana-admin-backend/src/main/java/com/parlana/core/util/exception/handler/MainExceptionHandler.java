package com.parlana.core.util.exception.handler;

import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.parlana.core.rest.response.dto.ErrorResponseDTO;
import com.parlana.core.util.exception.CentralMasterNotFoundException;

@ControllerAdvice
public class MainExceptionHandler {
     
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(CentralMasterNotFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDTO userNotFoundException(HttpServletRequest req, CentralMasterNotFoundException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String errorMessage = messageSource.getMessage("E004", null, locale);
        
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setCode(HttpStatus.NOT_FOUND.toString());
        errorResponse.setStatus("Failed");
        errorResponse.setMessage(errorMessage);
        errorResponse.setLink("www.mirkorodriguez.com");
        errorResponse.setDeveloperMessage(ex.getMessage());
         
        return errorResponse;
    }
    
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDTO userNotFoundException(HttpServletRequest req, SQLException ex) {        
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setCode(String.valueOf(ex.getErrorCode()));
        errorResponse.setStatus("Failed");
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setLink("www.mirkorodriguez.com");
        errorResponse.setDeveloperMessage(ex.getSQLState());
         
        return errorResponse;
    }
    
}

