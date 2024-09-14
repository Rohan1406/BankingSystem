package org.jsp.bankingProj;
import org.jsp.bankingProj.Transaction;

import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class TransactionDAO 
{
	private SessionFactory sessionFactory;
	
	public  TransactionDAO()
	{
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	public void saveTransaction(Transaction transaction)
	{
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		session.save(transaction);
		tx.commit();
		session.close();
	}
	public List<Transaction> getTransactionByAccountId(Long accountId)
	{
		Session session = sessionFactory.openSession();
		List<Transaction> transactions = session.createQuery("FROM Transaction WHERE account.id = :accountId",Transaction.class)
															.setParameter("accountId", accountId)
															.list();
		session.close();
		return transactions;
	}
}
