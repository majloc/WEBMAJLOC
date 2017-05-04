package fr.eni.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.eni.bean.Voiture;
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

	
}
