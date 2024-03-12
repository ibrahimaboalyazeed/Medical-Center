package com.global.medical.error;

public class InvalidStatusInputException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidStatusInputException(String message) {
        super(message);
    }
}

