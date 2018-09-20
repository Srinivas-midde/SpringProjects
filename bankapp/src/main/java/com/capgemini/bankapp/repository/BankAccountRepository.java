package com.capgemini.bankapp.repository;

import com.capgemini.bankapp.exception.AccountNotFoundException;

public interface BankAccountRepository {

	public double getBalance(long accountId) throws AccountNotFoundException;

	public boolean updateBalance(long accountId, double newBalance);
}
