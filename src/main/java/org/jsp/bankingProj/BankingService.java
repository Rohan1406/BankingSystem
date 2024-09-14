package org.jsp.bankingProj;

import java.util.Date;
import java.util.List;

public class BankingService 
{
	private AccountDAO accountDAO;
	private TransactionDAO transactionDAO;
	
	public BankingService()
	{
		accountDAO = new AccountDAO();
		transactionDAO = new TransactionDAO();
	}
	
	public Account createAccount(String holderName, double initialDeposit)
	{
		Account account = new Account();
		account.setHolderName(holderName);
		account.setAccountNumber(generateAccountNumber());
		account.setBalance(initialDeposit);
		accountDAO.saveAccount(account);
		
		if(initialDeposit > 0)
		{
			Transaction transaction = new Transaction();
			transaction.setAccount(account);
			transaction.setTransactionType("DEPOSIT");
			transaction.setTransactionDate(new Date());
			transactionDAO.saveTransaction(transaction);
		}
		return account;
	}

	public void depositMoney(String accountNumber, double amount)
	{
		Account account = accountDAO.getAccountByNumber(accountNumber);
		account.setBalance(account.getBalance() + amount);
		accountDAO.updateAccount(account);
		
		Transaction transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setTransactionType("DEPOSIT");
		transaction.setAmount(amount);
		transaction.setTransactionDate(new Date());
		transactionDAO.saveTransaction(transaction);
	}
	public void withdrawMoney(String accountNumber, double amount)
	{
		Account account = accountDAO.getAccountByNumber(accountNumber);
		if(account.getBalance() > amount)
		{
			account.setBalance(account.getBalance() - amount);
			accountDAO.updateAccount(account);
			
			Transaction transaction = new Transaction();
			transaction.setAccount(account);
			transaction.setTransactionType("WITHDARW");
			transaction.setAmount(amount);
			transaction.setTransactionDate(new Date());
			transactionDAO.saveTransaction(transaction);
		}
		else
		{
			throw new RuntimeException("Insufficient Balance.");
		}
	}
	public void transferMoney(String fromAccountNumber, String toAccountNumber, double amount)
	{
		Account fromAccount = accountDAO.getAccountByNumber(fromAccountNumber);
		Account toAccount = accountDAO.getAccountByNumber(toAccountNumber);
		
		if(fromAccount.getBalance() > amount)
		{
			fromAccount.setBalance(fromAccount.getBalance() - amount);
			toAccount.setBalance(toAccount.getBalance() + amount);
			accountDAO.updateAccount(fromAccount);
			accountDAO.updateAccount(toAccount);
			
			Transaction transactionFrom = new Transaction();
			transactionFrom.setAccount(fromAccount);
			transactionFrom.setTransactionType("TRANSFER OUT");
			transactionFrom.setAmount(amount);
			transactionFrom.setTransactionDate(new Date());
			transactionDAO.saveTransaction(transactionFrom);
			
			Transaction transactionTo = new Transaction();
			transactionTo.setAccount(toAccount);
			transactionTo.setTransactionType("TRANSFER IN");
			transactionTo.setAmount(amount);
			transactionTo.setTransactionDate(new Date());
			transactionDAO.saveTransaction(transactionTo);
		}
		else
		{
			throw new RuntimeException("Insufficient Balance.");
		}
	}
	public double checkBalance(String accountNumber)
	{
		Account account = accountDAO.getAccountByNumber(accountNumber);
		return account.getBalance();
	}
	public List<Transaction> getAccountStatement(String accountNumber)
	{
		Account account = accountDAO.getAccountByNumber(accountNumber);
		return transactionDAO.getTransactionByAccountId(account.getId());
	}
	private String generateAccountNumber() 
	{
		return "ACC" + System.currentTimeMillis();
	}
}
