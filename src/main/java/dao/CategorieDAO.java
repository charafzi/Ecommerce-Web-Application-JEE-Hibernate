package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bo.Categorie;
import bo.Produit;
import dto.CategorieDTO;
import service.CategorieService;

public class CategorieDAO {
	
	public List<Categorie> retrieve()
	{
		List<Categorie> categories = new ArrayList<>();
		Transaction t = null;
		try
		{
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			categories=session.createQuery("From Categorie").list();
			t.commit();
			session.close();
			return categories;
		
		}
		catch(HibernateException e)
		{
			t.rollback();
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Categorie getCategorieById(int id)
	{
		Categorie cat = null;
		Transaction t = null;
		try
		{
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			cat = session.find(Categorie.class, id);
			t.commit();
			session.close();
			return cat;
		
		}
		catch(HibernateException e)
		{
			t.rollback();
			e.printStackTrace();
			return cat;
		}
	}
	
	public boolean create(Categorie c)
	{

		Transaction t = null;
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
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
	
	public boolean delete(Categorie c)
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
 
	public static void main(String[] args) {
		CategorieDTO cat =  new CategorieService().getCategorieById(5);

		System.out.println(cat.getIdcat());
		
		System.out.println(cat.getProduits());
	}
}
