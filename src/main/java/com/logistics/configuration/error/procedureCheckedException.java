package com.logistics.configuration.error;

public class procedureCheckedException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "ms.saveErr";
	
    public procedureCheckedException (String message) {
    	super(message != null ? message : MESSAGE);
    }
}
