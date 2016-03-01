package tp4.univ.paris13;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;
import tp4.univ.paris13.model.Adresse;
import tp4.univ.paris13.model.Personne;

public class Test {
	public static void main(String[] args) {

		Session session = null;
			
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			Transaction tx = session.beginTransaction();
			
			List<Personne> personnes = session.createCriteria(Personne.class).list();
			System.out.println("With CRITERIA API : personnes : "+personnes.size());
			List<Personne> personnesHQL = session.createQuery("FROM Personne").list();
			System.out.println("WITH HQL QUERY : personnesHQL : "+personnesHQL.size());
			List<Personne> personnesSQL = session.createSQLQuery("select * from personne").list();
			System.out.println("WITH SQL QUERY : personnesSQL : "+personnesSQL.size());
			
			List<Adresse> adresses = session.createCriteria(Adresse.class).list();
			System.out.println("With CRITERIA API : adresses : "+adresses.size());
			List<Personne> adressesHQL = session.createQuery("FROM Adresse").list();
			System.out.println("WITH HQL QUERY : adressesHQL : "+adressesHQL.size());
			List<Personne> adressesSQL = session.createSQLQuery("select * from adresse").list();
			System.out.println("WITH SQL QUERY : adressesSQL : "+adressesSQL.size());
			
			/*
			System.out.println("La liste des personnes associés à  l'adresse 2:");
			Query req = session.createQuery("FROM Personne p where p.adresse.id = :x");
			req.setParameter("x", 2);
			List<Personne> personnes = req.list();
			for(Personne p : personnes){
				System.out.println(p.getId()+" - "+p.getNom()+" - "+p.getPrenom()+" - "
						+p.getMail()+" - "+p.getTel());
			}
			*/
			tx.commit();
			System.out.println("Done");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
}


