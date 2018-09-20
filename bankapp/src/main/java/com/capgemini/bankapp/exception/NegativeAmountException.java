package com.capgemini.bankapp.exception;

public class NegativeAmountException extends Exception {

	public NegativeAmountException(String message)
	{
		super(message);
	}
}
