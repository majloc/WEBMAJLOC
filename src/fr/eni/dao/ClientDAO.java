package fr.eni.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.eni.bean.Agence;
import fr.eni.bean.Client;
import fr.eni.util.DAOUtil;


public class ClientDAO {
	

	public static List<Client> findAll(){
		String req = "SELECT c FROM Client c";
		return DAOUtil
				.getEntityManager()
				.createQuery(req, Client.class)
				.getResultList();
		
	}
	
	
	public static Client findById(int id){
		return DAOUtil
				.getEntityManager()
				.find(Client.class, id);
	}
	
	

	public static void insert(Client c) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(c);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		
	}
	
	
	public static void remove(int id) throws Exception{
		remove(findById(id));
	}
	
	
	public static void remove(Client c) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.remove(c);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			throw e;
		}
		
	}
	
	
}
