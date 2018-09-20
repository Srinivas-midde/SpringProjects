package com.capgemini.bankapp.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.exception.AccountNotFoundException;
import com.capgemini.bankapp.repository.BankAccountRepository;
import com.capgemini.bankapp.util.DatabaseUtil;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

	@Autowired
	DatabaseUtil databaseUtil;
	
	@Override
	public double getBalance(long accountId) throws AccountNotFoundException {
		String query = "SELECT balance FROM accounts WHERE account_id = ?";
		try (Connection connection = databaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			
			statement.setLong(1, accountId);
			try(ResultSet result = statement.executeQuery()){
			if (result.next()) {
				return result.getDouble(1);
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new AccountNotFoundException("Account doesn't exist!");
	}

//	@Override
//	public double getBalance(long accountId) throws AccountNotFoundException {
//		for (BankAccount accounts : Database.account) {
//			if(accounts.getAccountId() == accountId) {
//				return accounts.getBalance();
//			}
//		}
//		throw new AccountNotFoundException("Account doesn't exist!");
//	}
//
	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		String query = "UPDATE accounts SET balance = ? WHERE account_id = ?";
		try (Connection connection = databaseUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setDouble(1, newBalance);
			statement.setLong(2, accountId);
			if(statement.executeUpdate() != 0) {
				System.out.println("Record inserted successfully");
			return true;}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

//	@Override
//	public boolean updateBalance(long accountId, double newBalance) {
//		for (BankAccount accounts : Database.account) {
//			if(accounts.getAccountId() == accountId) {
//				accounts.setBalance(newBalance);
//				return true;
//			}
//		}
//		return false;
//	}
}