package com.logistics.configuration.error;

public class DeleteCheckedException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "ms.deleteErr";
	
    public DeleteCheckedException () {
    	super(MESSAGE);
    }
    
}
