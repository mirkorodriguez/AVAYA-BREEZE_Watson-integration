package com.parlana.core.util.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 4650608893699863602L;
	
	protected String errorCode="Unknown_Exception";
	
	public BusinessException(){		
	}

	public BusinessException(String message, String errorCode){
		super(message);
		this.errorCode=errorCode;
	}

	public BusinessException(String message, Throwable cause){
		super(message,cause);
	}
	
	public BusinessException(String message){
		super(message);
	}
	
	public String getErrorCode(){
		return this.errorCode;
	}
}
