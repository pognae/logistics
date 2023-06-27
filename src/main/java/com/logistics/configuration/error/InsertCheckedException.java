package com.logistics.configuration.error;

public class InsertCheckedException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "ms.saveErr";
	
    public InsertCheckedException () {
    	super(MESSAGE);
    }
    
}
