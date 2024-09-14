package org.jsp.bankingProj;

import org.jsp.bankingProj.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AccountDAO 
{
	private SessionFactory sessionFactory;
	
	public AccountDAO() 
	{
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	public void saveAccount(Account account)
	{
		Session  session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(account);
		tx.commit();
		session.close();
	}
	public Account getAccountByNumber(String accountNumber)
	{
		Session  session = sessionFactory.openSession();
		Account account = session.createQuery("FROM Account WHERE accountNumber = :accountNumber", Account.class)
							.setParameter("accountNumber", accountNumber)
							.uniqueResult();
		session.close();
		return account;
	}
	public void updateAccount(Account account)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(account);
		tx.commit();
		session.close();
	}
}
