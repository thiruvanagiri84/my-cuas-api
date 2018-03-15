package com.thomsonreuters.cpl.cuasapi.exceptions;

public class CuasApiApplicationException extends RuntimeException{

	private static final long serialVersionUID = 400L;

	/**
	 * Constructs a new exception with null as its detail message. The cause is
	 * not initialized, and may subsequently be initialized by a call to
	 * Throwable.initCause(java.lang.Throwable).
	 */
	public CuasApiApplicationException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message. The cause
	 * is not initialized, and may subsequently be initialized by a call to
	 * 
	 * @link {Throwable.initCause(java.lang.Throwable)}.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later
	 *            retrieval by the @link {Throwable.getMessage()} method
	 */
	public CuasApiApplicationException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * Note that the detail message associated with cause is not automatically
	 * incorporated in this exception's detail message.
	 * 
	 * @param message
	 *            the detail message (which is saved for later retrieval by
	 *            the @link {Throwable.getMessage()} method).
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 * @link {Throwable.getCause()} method). (A null value is permitted, and
	 *       indicates that the cause is nonexistent or unknown.)
	 */
	public CuasApiApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message
	 * of <code>(cause==null ? null : cause.toString())</code> (which typically
	 * contains the class and detail message of cause). This constructor is
	 * useful for exceptions that are little more than wrappers for other
	 * throwables
	 * 
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 * @link {Throwable.getCause()} method). (A null value is permitted, and
	 *       indicates that the cause is nonexistent or unknown.)
	 */
	public CuasApiApplicationException(Throwable cause) {
		super(cause);
	}
	
}
