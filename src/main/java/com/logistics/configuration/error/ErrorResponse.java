package com.logistics.configuration.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
	
	private int code = HttpStatus.BAD_REQUEST.value();
    private Object message;

    public ErrorResponse(int code, Object message) {
        this.code = code;
        this.message = message;
    }
}
