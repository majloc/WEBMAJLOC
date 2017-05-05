package fr.eni.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.eni.bean.Agence;
import fr.eni.bean.Energie;
import fr.eni.bean.Type;
import fr.eni.bean.Voiture;
import fr.eni.util.DAOUtil;


public class VoitureDAO {
	
	public static List<Voiture> findAllCriteres(String criterelist){
		
		List<Voiture> voitures = new ArrayList<Voiture>();
			
		String[] criteres = criterelist.split(";");	
		String reqBase = "SELECT v FROM Voiture v WHERE ";
		
		String critere="";		
		if (("null".equals(criteres[0])) && ("null".equals(criteres[1])) && ("null".equals(criteres[2])) && ("null".equals(criteres[3])) && ("null".equals(criteres[4]))){
			return VoitureDAO.findAll();
		}
		
		for (int i = 0; i < criteres.length; i++) {
			if (!("null".equals(criteres[i]))){
				
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
				if (i < criteres.length && !("null".equals(criteres[i+1]))){
					reqBase+= " AND ";
				}		
			}
		}

			EntityManager em=DAOUtil.getEntityManager();
			TypedQuery<Voiture> tqv= em.createQuery(reqBase, Voiture.class);
			
		
			
			if (!("null".equals(criteres[0]))){
				Type typev = TypeDAO.findByLibelle(criteres[0]);
				tqv.setParameter("type", typev);
			}
			if (!("null".equals(criteres[1]))){
				Energie enerv = EnergieDAO.findByLibelle((criteres[1]));
				tqv.setParameter("energie", enerv);
			}
			if (!("null".equals(criteres[2]))){
				tqv.setParameter("marque", criteres[2]);
			}
			if (!("null".equals(criteres[3]))){
				tqv.setParameter("modele", criteres[3]);
			}
			if (!("null".equals(criteres[4]))){
				tqv.setParameter("immat", criteres[4]);
			}
						
			voitures = tqv.getResultList();
			
			return voitures;
			
	}
		
	
	public static List<Voiture> findAll(){
		String req = "SELECT v FROM Voiture v";
		return DAOUtil
				.getEntityManager()
				.createQuery(req, Voiture.class)
				.getResultList();
		
	}
	
	// methode qui renvoie soit les véhicules loués soit les véhicules dispos selon le statut
	public static List<Voiture> findByDispo(String statut){
		
		Boolean loue= false;
		
		switch (statut) {
			case "false":
				loue = false;
				break;
			
			case "true":
				loue = true;
				break;
		}
		
		String reqBase = "SELECT v FROM Voiture v WHERE loue = :loue ";
		
		return	DAOUtil.getEntityManager()
				.createQuery(reqBase, Voiture.class)
				.setParameter("loue", loue)
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
	

	public static String insert(Voiture v) throws Exception{
		
		String result="";
		
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(v);
			et.commit();
			result ="OK";
		} catch (Exception e) {
			et.rollback();
			result ="KO";
			throw e;
		}	
		 return result;
	}
	
	public static String update(Voiture v) throws Exception{
		
		String result="";
		
		Voiture vPersist = findById(v.getId());
			vPersist.setMarque(v.getMarque());
			vPersist.setModele(v.getModele());
			vPersist.setPlaque(v.getPlaque());
			vPersist.setNbPlace(v.getNbPlace());
			vPersist.setPhotos(v.getPhotos());
			vPersist.setPrixParJour(v.getPrixParJour());
			vPersist.setType(v.getType());				
			vPersist.setEnergie(v.getEnergie());
			vPersist.setAgence(v.getAgence());
			vPersist.setLoue(v.isLoue());
		
		EntityManager em = DAOUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.persist(vPersist);
			et.commit();
			result ="OK";
		} catch (Exception e) {
			et.rollback();
			result ="KO";
			throw e;
		}	
		 return result;
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
