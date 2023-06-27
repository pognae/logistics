package com.logistics.configuration.util.file;


public class FileUploadException extends RuntimeException {

	  
	public FileUploadException(String message) {
		super(message);
	}

	public FileUploadException(String message, Throwable cause) {
		super(message, cause);
	}
}