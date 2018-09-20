package com.capgemini.bankapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.capgemini.bankapp.config.AppConfig;
import com.capgemini.bankapp.controller.BankAccountController;
import com.capgemini.bankapp.exception.AccountNotFoundException;
import com.capgemini.bankapp.exception.InsufficientAccountBalanceException;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.exception.NegativeAmountException;

@Configuration

public class Application {

	public static void main(String[] args) throws LowBalanceException {
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("applicationContext.xml");

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		BankAccountController bankAccountController = context.getBean("bankAccountController",
				BankAccountController.class);
		try {
			System.out.println(bankAccountController.getBalance(12));
			System.out.println(bankAccountController.deposit(15, 5000));
			System.out.println(bankAccountController.withdraw(12, 3000));
			System.out.println(bankAccountController.getBalance(15));
			// System.out.println(bankAccountController.);
		} catch (AccountNotFoundException | LowBalanceException e) {
			if (e instanceof NegativeAmountException)
				System.out.println(e.getMessage());

			else if (e instanceof InsufficientAccountBalanceException)
				System.out.println(e.getMessage());
			else
				System.out.println(e.getMessage());

		}

	}

}
