package fr.eni.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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
	
	
	public static List<Location> findAllByFiltres(String filtres) {
		
		List<Location> locations = new ArrayList<Location>();
		
		String[] criteres = filtres.split(";");	
		String reqBase = "SELECT l FROM Location l WHERE ";
		
		String critere="";		
		if (("null".equals(criteres[0])) && ("null".equals(criteres[1])) && ("null".equals(criteres[2]))){
			return LocationDAO.findAll();
		}
		
		for (int i = 0; i < criteres.length; i++) {
			if (!("null".equals(criteres[i]))){
				
				switch (i) {
					case 0 : 
						critere = "client";
						break;
					case 1 : 
						critere = "voiture";
						break;
					case 2 : 
						critere = "restitution";
						break;
				}
				
				reqBase+= critere +" = :" +critere; 
				if (i < criteres.length && !("null".equals(criteres[i+1]))){
					reqBase+= " AND ";
				}		
			}
		}

			EntityManager em=DAOUtil.getEntityManager();
			TypedQuery<Location> tqv= em.createQuery(reqBase, Location.class);
			
			
			if (!("null".equals(criteres[0]))){
				Client client = ClientDAO.findById(Integer.parseInt(criteres[0]));
				tqv.setParameter("client", client);
			}
			if (!("null".equals(criteres[1]))){
				Voiture voiture = VoitureDAO.findById(Integer.parseInt(criteres[1]));
				tqv.setParameter("voiture", voiture);
			}
			if (!("null".equals(criteres[2]))){
				Boolean restitution = "true".equals(criteres[2]) ? true : false;
				tqv.setParameter("restitution", restitution);
			}
		
			locations = tqv.getResultList();
			
			return locations;
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
