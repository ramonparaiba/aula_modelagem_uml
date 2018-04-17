package com.pabloramon.aulauml.services.exceptions;

public class ObjectoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjectoNotFoundException (String msg) {
		super(msg);
	}
	
	public ObjectoNotFoundException (String msg, Throwable cause) {
		super(msg, cause);
	}

}
