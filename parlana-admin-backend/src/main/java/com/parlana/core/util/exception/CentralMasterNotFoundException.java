package com.parlana.core.util.exception;

public class CentralMasterNotFoundException extends BusinessException {

	private static final long serialVersionUID = 388354596992058821L;

	public CentralMasterNotFoundException(){
		
	}

	public CentralMasterNotFoundException(String message, String errorCode){
		super(message);
		this.errorCode=errorCode;
	}

	public CentralMasterNotFoundException(String message, Throwable cause){
		super(message,cause);
	}
}
