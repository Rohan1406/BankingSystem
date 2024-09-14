package org.jsp.bankingProj;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
	private Account account;

	@Column(name = "transaction_type", nullable = false)
	private String transactionType;

	@Column(name = "amount", nullable = false)
	private double amount;
	
	@Column(name = "transaction_date", nullable = false)
	private Date transactionDate;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
