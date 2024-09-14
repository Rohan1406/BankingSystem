package org.jsp.bankingProj;

import java.util.List;
import java.util.Scanner;

public class BankingSystemApp 
{
	public static void main(String[] args) 
	{
		BankingService bankingService = new BankingService();
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("Banking System");
			System.out.println("1. Create Account");
			System.out.println("2. Deposit Money");
			System.out.println("3. Withdraw Money");
			System.out.println("4. Transfer Money");
			System.out.println("5. Check Balance");
			System.out.println("6. Account Statement");
			System.out.println("7. Exit");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice)
			{
			case 1:
				System.out.println("Enter account holder name:");
				String holderName = sc.nextLine();
				System.out.println("Enter initial deposit amount:");
				double initialDeposit = sc.nextDouble();
				bankingService.createAccount(holderName, initialDeposit);
				System.out.println("Account Created Successfully.");
				break;
			case 2:
				System.out.println("Enter account number:");
				String accountNumberDeposit = sc.nextLine();
				System.out.println("Enter deposit amount:");
				double depositAmount = sc.nextDouble();
				bankingService.depositMoney(accountNumberDeposit, depositAmount);
				System.out.println("Money deposited successfully.");
				break;
			case 3:
				System.out.println("Enter account number:");
				String accountNumberWithdraw = sc.nextLine();
				System.out.println("Enter withdraw amount:");
				double withdrawalAmount = sc.nextDouble();
				try
				{
					bankingService.withdrawMoney(accountNumberWithdraw, withdrawalAmount);
					System.out.println("Money withdraw Successfully.");
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter source account number:");
				String fromAccountNumber = sc.nextLine();
				System.out.println("Enter destination account number:");
				String toAccountNumber = sc.nextLine();
				System.out.println("Enter transfer amount:");
				double transferAmount = sc.nextDouble();
				try
				{
					bankingService.transferMoney(fromAccountNumber, toAccountNumber, transferAmount);
					System.out.println("Money transferred successfully.");
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("Enter account number:");
				String accountNumberBalance = sc.nextLine();
				double balance = bankingService.checkBalance(accountNumberBalance);
				System.out.println("Current balance: " + balance);
				break;
			case 6:
				System.out.println("Enter account number:");
				String accountNumberStatement = sc.nextLine();
				List<Transaction> transactions = bankingService.getAccountStatement(accountNumberStatement);
				System.out.println("Account Statement.");
				for(Transaction transaction : transactions)
				{
					System.out.println(transaction.getTransactionType() + " - " + transaction.getAmount() + " on " + transaction.getTransactionDate());
				}
				break;
			case 7:
				System.out.println("Exiting...");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		}
	}
}
