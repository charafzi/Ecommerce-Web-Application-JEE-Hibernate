package dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bo.Achat;
import bo.LigneAchat;
public class AchatDAO {
	public boolean create(Achat achat) {
		Transaction tx=null;
		try {
		Session session=HibernateUtil.getSessionFactory().openSession();
		tx=session.beginTransaction();
		session.save(achat);
		for(LigneAchat l:achat.getLgAchats()) {
			l.getProduit().setStock(l.getProduit().getStock() - l.getQuantite());
			session.update(l.getProduit());
			l.setAchat(achat);
			session.saveOrUpdate(l);
		}
		
		tx.commit();
		session.close();
		return true;
	}
	catch(HibernateException e) {
		
		tx.rollback();
		e.printStackTrace();
		return false;
	}
		
	}

}
