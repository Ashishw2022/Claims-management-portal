package com.mfpe.company.exception;

public class MaximumClaimLimitReachedException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;

	public MaximumClaimLimitReachedException() {
		super();

	}

	public MaximumClaimLimitReachedException(String message) {
		super(message);

	}

}
