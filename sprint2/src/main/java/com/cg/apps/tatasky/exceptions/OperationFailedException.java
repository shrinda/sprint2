package com.cg.apps.tatasky.exceptions;



public class OperationFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperationFailedException(String name) {
		super(name);
	}
}
