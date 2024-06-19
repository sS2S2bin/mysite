package com.poscodx.mysite.exception;

public class UserRepositoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserRepositoryException() {
		super("UserRepository Exception occurs");
	}
	
	public UserRepositoryException(String error) {
		System.out.println(error);
	}
}
