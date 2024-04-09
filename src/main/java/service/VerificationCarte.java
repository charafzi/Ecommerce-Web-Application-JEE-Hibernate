package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import bo.Achat;
import bo.Client;
import bo.LigneAchat;
import dao.AchatDAO;
import dao.HibernateUtil;
import dto.AchatDTO;
import dto.ClientDTO;
import dto.LigneAchatDTO;

public class VerificationCarte {	
		public int verifierCarte(String cardnumber, String dateexp,String cardtype) {
		Transaction t=null;
		ClientDTO c = null;
		
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			
			
			Query query = session.createNativeQuery("SELECT * FROM CartesBancaires WHERE numero_carte = :num AND date_expiration = :date AND type_carte = :type");
	        query.setParameter("num", cardnumber);
	        query.setParameter("date", dateexp);
	        query.setParameter("type", cardtype);
	        
	        List results = query.list();
	        int verifier = 0;
	        if (!results.isEmpty()) {
	            verifier = 1;
	        }
			
			t.commit();
			session.close();
			return verifier;
	}
		catch(HibernateException e)
		{
			t.rollback();
			e.printStackTrace();
			return 0;
		}
	}
		
		public static void main(String[] args) {
			System.out.println(new VerificationCarte().verifierCarte("1234567890123456", "05/25", "Visa"));
		}

}
