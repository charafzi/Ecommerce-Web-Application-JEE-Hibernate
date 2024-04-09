package dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import bo.Client;
import bo.Produit;
import oracle.net.aso.s;

public class ProduitDAO {
	
	public boolean create(Produit p)
	{
		Transaction t = null;
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			session.save(p);
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
	
	public boolean update(Produit p)
	{
		Transaction t = null;
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			session.saveOrUpdate(p);
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
	
	public boolean delete(Produit p)
	{
		Transaction t = null;
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			session.delete(p);
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
	
	public List<Produit> retrieve()
	{
		List<Produit> produits = new ArrayList<>();
		Transaction t = null;
		try
		{
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			produits=session.createQuery("From Produit P where P.stock>0").list();
			t.commit();
			session.close();
			return produits;
		
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Produit> retrieve(int idcat)
	{
		Transaction t = null;
		try
		{
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			Query<Produit> query = session.createQuery("From Produit P JOIN P.categorie c WHERE c.id = :idcat", Produit.class);
	        query.setParameter("idcat", idcat);
	        List<Produit> results = query.list();
			
			t.commit();
			session.close();
			return results;
		
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Produit getProductById(int id)
	{
		Transaction t = null;
		Produit p = null;
		
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			p = session.find(Produit.class, id);
			session.close();
			return p;
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return p;
		}
	}
	
	public List<Produit> retrieve(String motcle)
	{
		Transaction t = null;
		
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			
			Query<Produit> query = session.createQuery("From Produit P WHERE nom like :mc", Produit.class);
	        query.setParameter("mc", "%" + motcle + "%");
	        List<Produit> results = query.list();
			
			t.commit();
			session.close();
			return results;
		}
		catch(HibernateException e)
		{
			t.rollback();
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean verifierQuantite(int id,int qte)
	{
		Transaction t = null;
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			
			 Query query = session.createNativeQuery("SELECT stock FROM Produit WHERE idprod = :id");
	         query.setParameter("id",id);
	         BigDecimal quantiteEnStock = (BigDecimal) query.uniqueResult();

			
			t.commit();
			session.close();
			return quantiteEnStock.compareTo(BigDecimal.valueOf(qte)) >= 0;
		}
		catch(HibernateException e)
		{
			t.rollback();
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(new ProduitDAO().verifierQuantite(25, 101));
	}
	

}
