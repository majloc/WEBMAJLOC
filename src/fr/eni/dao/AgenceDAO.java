package fr.eni.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.eni.bean.Agence;
import fr.eni.util.DAOUtil;


public class AgenceDAO {
	

	public static List<Agence> findAll(){
		String req = "SELECT a FROM Agence a";
		return DAOUtil
				.getEntityManager()
				.createQuery(req, Agence.class)
				.getResultList();
		
	}
	
	
	public static Agence findById(int id){
		return DAOUtil
				.getEntityManager()
				.find(Agence.class, id);
	}
	
	

	public static void insert(Agence a) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(a);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		
	}
	
	
	public static void remove(int id) throws Exception{
		remove(findById(id));
	}
	
	
	public static void remove(Agence a) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.remove(a);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		
	}
	
	
}
