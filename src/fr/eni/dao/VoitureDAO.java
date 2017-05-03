package fr.eni.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.eni.bean.Agence;
import fr.eni.bean.Energie;
import fr.eni.bean.EnumEnergie;
import fr.eni.bean.EnumType;
import fr.eni.bean.Type;
import fr.eni.bean.Voiture;
import fr.eni.util.DAOUtil;


public class VoitureDAO {
	
		public static List<Voiture> findAllCriteres(String[] criteres){
		String reqBase = "SELECT v FROM Voiture v WHERE ";
		
		String critere="";		
		if (("".equals(criteres[0])) && ("".equals(criteres[1])) && ("".equals(criteres[2])) && ("".equals(criteres[3])) && ("".equals(criteres[4]))){
			return VoitureDAO.findAll();
		}
		
		for (int i = 0; i < criteres.length; i++) {
			if (!("".equals(criteres[i]))){
				
				switch (i) {
					case 0 : 
						critere = "type";
						break;
					case 1 : 
						critere = "energie";
						break;
					case 2 : 
						critere = "marque";
						break;
					case 3 : 
						critere = "modele";
						break;	
					case 4 : 
						critere = "immat";
						break;
				}
				
				reqBase+= critere +" = :" +critere; 
				if (i < criteres.length && !("".equals(criteres[i+1]))){
					reqBase+= " AND ";
				}		
			}
		}

			EntityManager em=DAOUtil.getEntityManager();
			TypedQuery<Voiture> tqv= em.createQuery(reqBase, Voiture.class);
			
		
			
			if (!("".equals(criteres[0]))){
				Type typev = TypeDAO.findByLibelle(criteres[0]);
				tqv.setParameter("type", typev);
			}
			if (!("".equals(criteres[1]))){
				Energie enerv = EnergieDAO.findByLibelle((criteres[1]));
				tqv.setParameter("energie", enerv);
			}
			if (!("".equals(criteres[2]))){
				tqv.setParameter("marque", criteres[2]);
			}
			if (!("".equals(criteres[3]))){
				tqv.setParameter("modele", criteres[3]);
			}
			if (!("".equals(criteres[4]))){
				tqv.setParameter("immat", criteres[4]);
			}
			
			return tqv.getResultList();
			
	}
		
	
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
		String req = "SELECT v FROM Voiture v WHERE agence = :agence";
		return DAOUtil
				.getEntityManager()
				.createQuery(req, Voiture.class)
				.setParameter("agence", agence)
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
