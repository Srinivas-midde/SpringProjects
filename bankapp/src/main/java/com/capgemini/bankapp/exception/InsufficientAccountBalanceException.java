package com.capgemini.bankapp.exception;

public class InsufficientAccountBalanceException extends Exception {

	public InsufficientAccountBalanceException(String message)
	{
		super(message);
	}
	
}
