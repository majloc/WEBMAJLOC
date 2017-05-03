package fr.eni.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.eni.bean.Agence;
import fr.eni.bean.Energie;
import fr.eni.bean.Type;
import fr.eni.bean.Voiture;
import fr.eni.util.DAOUtil;


public class VoitureDAO {
	

	public static List<Voiture> findAll(){
		String req = "SELECT v FROM Voiture v";
		return DAOUtil
				.getEntityManager()
				.createQuery(req, Voiture.class)
				.getResultList();
		
	}
	
	
	public static Voiture findById(int id){
		return DAOUtil
				.getEntityManager()
				.find(Voiture.class, id);
	}
	
	
	public static List<Voiture> findAllByAgence(Agence agence){
		String req = "SELECT v FROM Voiture v WHERE agence = :var";
		return DAOUtil
				.getEntityManager()
				.createQuery(req, Voiture.class)
				.setParameter("var", agence)
				.getResultList();
		
	}
	
	
	public static List<Voiture> findAllByType(Type type, Agence agence){
		String req = "SELECT v FROM Voiture v WHERE type = :var AND agence = :var1";
		return DAOUtil
				.getEntityManager()
				.createQuery(req, Voiture.class)
				.setParameter("var", type)
				.setParameter("var1", agence)
				.getResultList();
		
	}
	
	public static List<Voiture> findAllByEnergie(Energie energie, Agence agence){
		String req = "SELECT v FROM Voiture v WHERE energie = :var AND agence = :var1";
		return DAOUtil
				.getEntityManager()
				.createQuery(req, Voiture.class)
				.setParameter("var", energie)
				.setParameter("var1", agence)
				.getResultList();
		
	}	
	


	public static void insert(Voiture v) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(v);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		
	}
	
	
	public static void remove(int id) throws Exception{
		remove(findById(id));
	}
	
	
	public static void remove(Voiture v) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.remove(v);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		
	}
	
	
}
