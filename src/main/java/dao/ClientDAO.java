package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bo.Client;



public class ClientDAO {
	public boolean create(Client c)
	{
		Transaction t = null;
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			System.out.println("----------------------------------->DAO : "+c.getPassword());
			session.save(c);
			t.commit();
			session.close();
			return true;
		
		}
		catch(HibernateException e)
		{
			t.rollback();
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean update(Client c)
	{
		Transaction t = null;
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			session.saveOrUpdate(c);
			t.commit();
			session.close();
			return true;
		
		}
		catch(HibernateException e)
		{
			t.rollback();
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Client c)
	{
		Transaction t = null;
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			session.delete(c);
			t.commit();
			session.close();
			return true;
		
		}
		catch(HibernateException e)
		{
			t.rollback();
			e.printStackTrace();
			return false;
		}
	}
	
	public Client findById(int id) {
		Transaction tx=null;
		Client c = null;
			try {
			Session session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			c=session.find(Client.class, id);
			tx.commit();
			session.close();
			return c;
			
			
		}
		catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
			return c;
		
		}

	}
	
	
}


