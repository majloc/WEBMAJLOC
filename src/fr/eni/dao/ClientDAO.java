package fr.eni.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;


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
	
	
	public static List<Client> findAllByFiltres(String filtreslist) {
		
		List<Client> clients = new ArrayList<Client>();
		
		String[] criteres = filtreslist.split(";");	
		String reqBase = "SELECT c FROM Client c WHERE ";
		
		String critere="";		
		if (("null".equals(criteres[0])) && ("null".equals(criteres[1])) && ("null".equals(criteres[2])) && ("null".equals(criteres[3]))){
			return ClientDAO.findAll();
		}
		
		for (int i = 0; i < criteres.length; i++) {
			if (!("null".equals(criteres[i]))){
				
				switch (i) {
					case 0 : 
						critere = "nom";
						break;
					case 1 : 
						critere = "prenom";
						break;
					case 2 : 
						critere = "mail";
						break;
					case 3 : 
						critere = "tel";
						break;	
				}
				
				reqBase+= critere +" LIKE :" +critere; 
				if (i < criteres.length && !("null".equals(criteres[i+1]))){
					reqBase+= " AND ";
				}		
			}
		}

			EntityManager em=DAOUtil.getEntityManager();
			TypedQuery<Client> tqv= em.createQuery(reqBase, Client.class);
			
			
			if (!("null".equals(criteres[0]))){
				tqv.setParameter("nom", "%"+criteres[0]+"%");
			}
			if (!("null".equals(criteres[1]))){
				tqv.setParameter("prenom", "%"+criteres[1]+"%");
			}
			if (!("null".equals(criteres[2]))){
				tqv.setParameter("mail", "%"+criteres[2]+"%");
			}
			if (!("null".equals(criteres[3]))){
				tqv.setParameter("tel", "%"+criteres[3]+"%");
			}
						
			clients = tqv.getResultList();
			
			return clients;

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
