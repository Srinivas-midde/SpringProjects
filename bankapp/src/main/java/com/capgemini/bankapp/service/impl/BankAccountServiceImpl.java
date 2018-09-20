package com.capgemini.bankapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp.exception.AccountNotFoundException;
import com.capgemini.bankapp.exception.InsufficientAccountBalanceException;
import com.capgemini.bankapp.exception.NegativeAmountException;
import com.capgemini.bankapp.repository.BankAccountRepository;
import com.capgemini.bankapp.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository bankRepository;

	@Override
	public double getBalance(long accountId) throws AccountNotFoundException {
		return bankRepository.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount) throws AccountNotFoundException {
		double accountBalance = bankRepository.getBalance(accountId);
		bankRepository.updateBalance(accountId, accountBalance - amount);
		return accountBalance - amount;
	}

	@Override
	public double deposit(long accountId, double amount) throws AccountNotFoundException {
		double accountBalance = bankRepository.getBalance(accountId);
		bankRepository.updateBalance(accountId, accountBalance + amount);
		return accountBalance + amount;
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount)
			throws InsufficientAccountBalanceException, NegativeAmountException, AccountNotFoundException {
		double accountBalanceFrom = bankRepository.getBalance(fromAcc);
		
		if (accountBalanceFrom < amount) 
			throw new InsufficientAccountBalanceException("There isn't sufficient balance in your account!");
		else if (amount < 0)
			throw new NegativeAmountException("The amount cannot be negative!");
		else {
			BankAccountServiceImpl serviceobject;
			serviceobject = new BankAccountServiceImpl();
			serviceobject.deposit(toAcc, amount);
			serviceobject.withdraw(fromAcc, amount);
			return true;
		}
	}

}
