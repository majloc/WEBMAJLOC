package fr.eni.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.eni.bean.Agence;
import fr.eni.bean.Energie;
import fr.eni.bean.EnumEnergie;
import fr.eni.bean.EnumType;
import fr.eni.bean.Type;
import fr.eni.bean.Voiture;
import fr.eni.util.DAOUtil;


public class EnergieDAO {
	

	public static List<Energie> findAll(){
		String req = "SELECT e FROM Energie e";
		return DAOUtil
				.getEntityManager()
				.createQuery(req, Energie.class)
				.getResultList();
		
	}
	
	
	public static Energie findById(int id){
		return DAOUtil
				.getEntityManager()
				.find(Energie.class, id);
	}
	
	
	public static Energie findByLibelle(String libelle){
EnumEnergie enumenergie = null;
		
		switch(libelle){
		
		case "diesel":
			enumenergie = EnumEnergie.diesel;
			break;
		case "electrique":
			enumenergie = EnumEnergie.electrique;
			break;
		case "essence":
			enumenergie = EnumEnergie.essence;
			break;
		case "hybride":
			enumenergie = EnumEnergie.hybride;
			break;
			
		}
		
		String requete = "SELECT e FROM Energie e WHERE energie =:energie";
		
		return DAOUtil.getEntityManager().createQuery(requete,Energie.class).setParameter("energie", enumenergie).getSingleResult();
		
	}
	

	public static void insert(Energie e) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(e);
			et.commit();
		} catch (Exception ex) {
			et.rollback();
			throw ex;
		}
		
	}
	
	
	public static void remove(int id) throws Exception{
		remove(findById(id));
	}
	
	
	public static void remove(Energie e) throws Exception{
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.remove(e);
			et.commit();
		} catch (Exception ex) {
			et.rollback();
			throw ex;
		}
		
	}
	
	
}
