package service;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import bo.Client;
import dao.HibernateUtil;
import dto.ClientDTO;


public class Authentification implements AuthentificationInterface{

	@Override
	public ClientDTO loginClient(String login, String password) {
		Transaction t=null;
		ClientDTO c = null;
		
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			
			
			Query<Client> query = session.createQuery("FROM Client WHERE login = :login AND password = :password", Client.class);
	        query.setParameter("login", login);
	        query.setParameter("password", password);
	        List<Client> results = query.list();
	        
	        if (!results.isEmpty()) {
	        	c = new ClientService().fromClient(results.get(0));
	        }
			
			t.commit();
			session.close();
			return c;
	}
		catch(HibernateException e)
		{
			t.rollback();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int loginAdmin(String login, String password) {
			Transaction t=null;
			
			try
			{
				Session session = HibernateUtil.getSessionFactory().openSession();
				t = session.beginTransaction();
				
				
				Query query = session.createNativeQuery("Select * FROM Admin WHERE login = :login AND password = :password");
		        query.setParameter("login", login);
		        query.setParameter("password", password);
		        Object result = query.uniqueResult();

		        int res= 0;
		        if (result != null) 
		        {
		           res=1;
		        }
		       
		        
		        
				t.commit();
				session.close();
				return res;
				
		}
			catch(HibernateException e)
			{
				t.rollback();
				e.printStackTrace();
				return 0;
			}
	}
	
	public static void main(String[] args) {
		
		System.out.println(new Authentification().loginAdmin("ghaziachraf99@gmail.com", "123"));
	}

}
