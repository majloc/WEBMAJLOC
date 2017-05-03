package fr.eni.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.bean.Agence;
import fr.eni.bean.Client;
import fr.eni.bean.Energie;
import fr.eni.bean.EnumEnergie;
import fr.eni.bean.EnumType;
import fr.eni.bean.Location;
import fr.eni.bean.Type;
import fr.eni.bean.Voiture;
import fr.eni.dao.AgenceDAO;
import fr.eni.dao.ClientDAO;
import fr.eni.dao.EnergieDAO;
import fr.eni.dao.LocationDAO;
import fr.eni.dao.TypeDAO;
import fr.eni.dao.VoitureDAO;
import fr.eni.util.DAOUtil;


public class TestVoitures {

	public static void main(String[] args) {
		
		
		//Types
		Type t1 = new Type(EnumType.berline);
		Type t2 = new Type(EnumType.citadine);
		Type t3 = new Type(EnumType.monospace);
		Type t4 = new Type(EnumType.utilitaire);
		
		try {
			TypeDAO.insert(t1);
			TypeDAO.insert(t2);
			TypeDAO.insert(t3);
			TypeDAO.insert(t4);
		} catch (Exception e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
				
		
		//Energies
		Energie e1 = new Energie(EnumEnergie.diesel);
		Energie e2 = new Energie(EnumEnergie.electrique);
		Energie e3 = new Energie(EnumEnergie.essence);
		Energie e4 = new Energie(EnumEnergie.hybride);
		
		try {
			EnergieDAO.insert(e1);
			EnergieDAO.insert(e2);
			EnergieDAO.insert(e3);
			EnergieDAO.insert(e4);
		} catch (Exception e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		
		// Agences
		Agence a= new Agence("Barbier", "Mickael", "LocaWhaa", "majloc","majloc3","mb@gmail.com","0102030405",185956.74);
		Agence a2= new Agence("Piron", "Axel", "LocaYeah", "majloc","majloc3","ap@gmail.com","0102030405",185956.74);
		
		try {
			AgenceDAO.insert(a);
			AgenceDAO.insert(a2);
		} catch (Exception e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}	
		
				
		List<String> listePhotos= new ArrayList<>();
		listePhotos.add("photo1");
		listePhotos.add("photo2");
		Voiture v1 = new Voiture(35.8,"AA-999-AA", "Renault", "Scenic", 7, listePhotos, t3, e1, a); 
		Voiture v2 = new Voiture(23,"BB-111-BB", "Renault", "Clio", 5, listePhotos, t2, e3, a2); 
		Voiture v3 = new Voiture(58.9,"CC-222-CC", "Lexus", "GS 300h", 5, listePhotos, t1, e4, a); 
		
		try {
			VoitureDAO.insert(v1);
			VoitureDAO.insert(v2);
			VoitureDAO.insert(v3);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Liste de tous les voitures (toutes agences confondues)");
		List<Voiture> listeV = VoitureDAO.findAll();
		for (Voiture voiture : listeV) {
			System.out.println(voiture);
		}
		

		System.out.println("\nVoiture de l'agence "+ a.getAgence());
		listeV = VoitureDAO.findAllByAgence(a);
		for (Voiture voiture : listeV) {
			System.out.println(voiture);
		}
		
		System.out.println("\nVoiture de l'agence " + a2.getAgence());
		listeV = VoitureDAO.findAllByAgence(a2);
		for (Voiture voiture : listeV) {
			System.out.println(voiture);
		}
		
		System.out.println("\n Voiture de type " + t1.getType() + " de l'agence " + a.getAgence());
		listeV = VoitureDAO.findAllByType(t1, a);
		for (Voiture voiture : listeV) {
			System.out.println(voiture);
		}
		
		System.out.println("\n Voiture avec energie " + e1.getEnergie() + " de l'agence " + a.getAgence());
		listeV = VoitureDAO.findAllByEnergie(e1, a);
		for (Voiture voiture : listeV) {
			System.out.println(voiture);
		}
		
//		System.out.println("\nSuppression de v1");
//		try {
//			VoitureDAO.remove(v1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("\nSuppression de id=2");
//		try {
//			VoitureDAO.remove(2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//
//		System.out.println("\nListe de toutes les voitures apres suppression :");
//		listeV = VoitureDAO.findAll();
//		for (Voiture voiture : listeV) {
//			System.out.println(voiture);
//		}
		
		//Clients
		Client c1 = new Client("Martin","John"," rue ici ", "john.martin@gmail.com", "0101010101");
		Client c2 = new Client("Dupond","Bob"," rue la ", "bob.dupond@gmail.com", "0202020202");
		Client c3 = new Client("Legrand","Steven"," rue la bas ", "steven.legrand@gmail.com", "0303030303");
		
		try {
			ClientDAO.insert(c1);
			ClientDAO.insert(c2);
			ClientDAO.insert(c3);
		} catch (Exception e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		
		
		//Locations
		Location l1 = new Location(new Date(), new Date(2017, 4, 4), listePhotos, listePhotos , false, v2, c1, 0.00);
		Location l2 = new Location(new Date(2017, 3, 29), new Date(2017, 4, 2), listePhotos, listePhotos , true, v1, c3, 0.00);
		
		try {
			LocationDAO.insert(l1);
			LocationDAO.insert(l2);
		} catch (Exception e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		
		
		DAOUtil.close();
	}

}
