package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bo.Produit;


public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	static {
		try 
		{
			Configuration config = new Configuration();
			config.configure("dao/hibernate.cfg.xml");
			sessionFactory = config.buildSessionFactory();
		}
		catch(HibernateException e)
		{
			System.err.println(e.getMessage());
			sessionFactory=null;
		}
		
	}
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	public static void main(String[] b)
	{
		Transaction t = null;
		System.out.println(HibernateUtil.getSessionFactory());
		Session session = HibernateUtil.getSessionFactory().openSession();
		t = session.beginTransaction();
		try
		{
			//Produit p = session.find(Produit.class, 2);
			Produit p = new Produit(1,"Prod1","xxx",50.00);
			session.save(p);
			System.out.println(p.getDesignation());
			t.commit();
			session.close();
			System.out.println("test");
			
		}
		catch(Exception e)
		{
			t.rollback();
			System.err.println(e.toString());
			
		}
	}

}

