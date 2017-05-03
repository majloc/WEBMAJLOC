package fr.eni.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.eni.bean.Agence;
import fr.eni.bean.Type;
import fr.eni.util.DAOUtil;


public class TypeDAO {
	

	public static List<Type> findAll(){
		String req = "SELECT t FROM Type t";
		return DAOUtil
				.getEntityManager()
				.createQuery(req, Type.class)
				.getResultList();
		
	}
	
	
	public static Type findById(int id){
		return DAOUtil
				.getEntityManager()
				.find(Type.class, id);
	}
	
	

	public static void insert(Type t) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(t);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		
	}
	
	
	public static void remove(int id) throws Exception{
		remove(findById(id));
	}
	
	
	public static void remove(Type t) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.remove(t);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		
	}
	
	
}
