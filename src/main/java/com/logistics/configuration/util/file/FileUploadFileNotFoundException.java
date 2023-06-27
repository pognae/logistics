package com.logistics.configuration.util.file;

 

public class FileUploadFileNotFoundException extends FileUploadException {

	public FileUploadFileNotFoundException(String message) {
		super(message);
	}

	public FileUploadFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}