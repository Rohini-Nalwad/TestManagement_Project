package com.exception;

public class QuestionNotFoundException extends RuntimeException {

   	private static final long serialVersionUID = 1L;

	public QuestionNotFoundException(String message, Object object) {
        super(message);
    }
}
