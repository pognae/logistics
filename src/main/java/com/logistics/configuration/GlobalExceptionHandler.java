package com.logistics.configuration;

import java.net.BindException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.logistics.configuration.error.DeleteCheckedException;
import com.logistics.configuration.error.DuplicateKeyAutowiredException;
import com.logistics.configuration.error.ErrorResponse;
import com.logistics.configuration.error.InsertCheckedException;
import com.logistics.configuration.error.UpdateCheckedException;
import com.logistics.configuration.error.procedureCheckedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired MessageSource msg;
	
	@ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception ex, Model model){
		System.out.println("::::::::::::: GlobalExceptionHandler ::::::::::::::::");
		log.error("handleException: {}", ex.getMessage());
		System.out.println("::::::::::::: GlobalExceptionHandler ::::::::::::::::");
		
		//ErrorResponse response = new ErrorResponse(999, ex.getMessage());
		//return new ResponseEntity<>(response, HttpStatus.OK);	//에러로 떨어지지 않는 부분
		
		return ResponseEntity.badRequest().body( new ErrorResponse(999, ex.getMessage()) );	//에러로 떨어지는 부분
    }
	
	/*common error*/
    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<ErrorResponse> errorHandler(BindException e) {
        return ResponseEntity.badRequest().body( new ErrorResponse(400, e.getMessage()) );
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> errorHandler(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body( new ErrorResponse(400, e.getMessage()) );
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<ErrorResponse> errorHandler(NullPointerException e){
        return ResponseEntity.badRequest().body( new ErrorResponse(400, e.getMessage()) );
    }
    
    @ExceptionHandler(value = {DuplicateKeyException.class})
    public ResponseEntity<ErrorResponse> errorHandler(DuplicateKeyException e){
    	DuplicateKeyAutowiredException Dup = new DuplicateKeyAutowiredException();
    	return ResponseEntity.badRequest().body( new ErrorResponse(999, msg.getMessage(Dup.getMessage(), null, LocaleContextHolder.getLocale())) );
    }
    
    @ExceptionHandler(value = {InsertCheckedException.class})
	public ResponseEntity<ErrorResponse> errorHandler(InsertCheckedException e){
    	return ResponseEntity.badRequest().body( new ErrorResponse(999, msg.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale())));
	}
    
    @ExceptionHandler(value = {UpdateCheckedException.class})
    public ResponseEntity<ErrorResponse> errorHandler(UpdateCheckedException e){
    	System.out.println(e.getMessage());
    	return ResponseEntity.badRequest().body( new ErrorResponse(999, msg.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale())));
    }
    
    @ExceptionHandler(value = {DeleteCheckedException.class})
    public ResponseEntity<ErrorResponse> errorHandler(DeleteCheckedException e){
    	return ResponseEntity.badRequest().body( new ErrorResponse(999, msg.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale())));
    }
    
    @ExceptionHandler(value = {procedureCheckedException.class})
    public ResponseEntity<ErrorResponse> errorHandler(procedureCheckedException e){
    	return ResponseEntity.badRequest().body( new ErrorResponse(999, msg.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale())));
    }

    /*Http error*/
    @ExceptionHandler(value = {HttpClientErrorException.class})
    public ResponseEntity<ErrorResponse> errorHandler(HttpClientErrorException e){
        return ResponseEntity.badRequest().body( new ErrorResponse(404, e.getMessage()) );
    }
    
    @ExceptionHandler(value = {HttpServerErrorException .class})
    public ResponseEntity<ErrorResponse> errorHandler(HttpServerErrorException e){
        return ResponseEntity.badRequest().body( new ErrorResponse(500, e.getMessage()) );
    }
    
    @ExceptionHandler(value = {UnknownHttpStatusCodeException .class})
    public ResponseEntity<ErrorResponse> errorHandler(UnknownHttpStatusCodeException e){
        return ResponseEntity.badRequest().body( new ErrorResponse(400, e.getMessage()) );
    }

	
	
}
