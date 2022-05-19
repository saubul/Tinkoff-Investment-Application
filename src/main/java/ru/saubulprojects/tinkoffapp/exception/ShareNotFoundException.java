package ru.saubulprojects.tinkoffapp.exception;

public class ShareNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2032472509785666923L;

	public ShareNotFoundException(String message) {
		super(message);
	}
}
