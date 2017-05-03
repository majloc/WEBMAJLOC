package fr.eni.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.eni.bean.Agence;
import fr.eni.bean.Client;
import fr.eni.bean.Location;
import fr.eni.bean.Voiture;
import fr.eni.util.DAOUtil;


public class LocationDAO {
	

	public static List<Location> findAll(){
		String req = "SELECT l FROM Location l";
		return DAOUtil
				.getEntityManager()
				.createQuery(req, Location.class)
				.getResultList();
		
	}
	
	
	public static Location findById(int id){
		return DAOUtil
				.getEntityManager()
				.find(Location.class, id);
	}
	

	public static void insert(Location l) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(l);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		
	}
	
	
	public static void remove(int id) throws Exception{
		remove(findById(id));
	}
	
	
	public static void remove(Location l) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.remove(l);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		
	}
	
	
}
