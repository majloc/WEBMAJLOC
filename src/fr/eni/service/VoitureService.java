package fr.eni.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import fr.eni.bean.Agence;
import fr.eni.bean.Energie;
import fr.eni.bean.Type;
import fr.eni.bean.Voiture;
import fr.eni.dao.AgenceDAO;
import fr.eni.dao.EnergieDAO;
import fr.eni.dao.TypeDAO;
import fr.eni.dao.VoitureDAO;

@Path("/voiture")
public class VoitureService {

	
@GET
@Path("/allcars")
@Produces(MediaType.APPLICATION_JSON)
public List<Voiture> getall(){
	return VoitureDAO.findAll();
}
	
@GET
@Path("/carsbycriteres")
@Produces(MediaType.APPLICATION_JSON)
public List<Voiture> getAllByCritere(@QueryParam("criteres") String criteres){
	return VoitureDAO.findAllCriteres(criteres);
}

@GET
@Path("/statut")
@Produces(MediaType.APPLICATION_JSON)
public List<Voiture> getAllDispo(@QueryParam("loue") String statut){
	return VoitureDAO.findByDispo(statut);
}

@POST
@Path("/newcar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public String createVoiture(
		@FormParam("marque") String marque,
		@FormParam("modele") String modele,
		@FormParam("energie") String energie,
		@FormParam("type") String type,
		@FormParam("plaque") String plaque,
		@FormParam("nbPlace") String nbPlace,
		@FormParam("loue") String loue,
		@FormParam("photos") String photos,
		@FormParam("agence") String agence,
		@FormParam("prixParJour") String prixParJour,
		@Context HttpServletResponse servletResponse) throws IOException {
			
		// Chaine qui sera renvoyée si l'insertion s'est bien passée, OK ou KO
		String result =""; 
	
			Voiture newCar = new Voiture();
			newCar.setMarque(marque);
			newCar.setModele(modele);
			newCar.setEnergie(EnergieDAO.findByLibelle(energie));
			newCar.setType(TypeDAO.findByLibelle(type));
			newCar.setPlaque(plaque);
			newCar.setNbPlace(Integer.parseInt(nbPlace));
			newCar.setLoue("true".equals(loue) ? true : false);
			String[] tabPhotos = photos.split(";");
			List<String> listPhotos = new ArrayList<String>();
			for (String photo : tabPhotos) {
				listPhotos.add(photo);
			}
			newCar.setPhotos(listPhotos);
			newCar.setAgence(AgenceDAO.findByLibelle(agence));
			newCar.setPrixParJour(Double.parseDouble(prixParJour));
						
			try {
				result = VoitureDAO.insert(newCar);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
	}

@POST
@Path("/modifcar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public String updateVoiture(
		@FormParam("id") int id,
		@FormParam("marque") String marque,
		@FormParam("modele") String modele,
		@FormParam("energie") String energie,
		@FormParam("type") String type,
		@FormParam("plaque") String plaque,
		@FormParam("nbPlace") String nbPlace,
		@FormParam("loue") String loue,
		@FormParam("photos") String photos,
		@FormParam("agence") String agence,
		@FormParam("prixParJour") String prixParJour,
		@Context HttpServletResponse servletResponse) throws IOException {
			
		// Chaine qui sera renvoyée si l'insertion s'est bien passée, OK ou KO

			String result="";
	
			String[] tabPhotos = photos.split(";");
			List<String> listPhotos = new ArrayList<String>();
			for (String photo : tabPhotos) {
				listPhotos.add(photo);
			}
			
			Voiture modifCar = new Voiture(id, Double.parseDouble(prixParJour),plaque ,marque,					
					modele, Integer.parseInt(nbPlace),"true".equals(loue) ? true : false,listPhotos, 
					TypeDAO.findByLibelle(type),EnergieDAO.findByLibelle(energie), AgenceDAO.findByLibelle(agence));
						
			try {
				result = VoitureDAO.update(modifCar);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
	}
	
}
