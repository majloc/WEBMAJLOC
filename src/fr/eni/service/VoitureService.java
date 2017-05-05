package fr.eni.service;

import java.io.IOException;
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


//public List<Voiture> updateVoiture(
//		@FormParam("marque") String marque,
//		@FormParam ("profession") String profession,
//		@Context HttpServletResponse servletResponse) throws IOException{
//		Voiture newCar = new Voiture();
//		newCar
//		
//		int result = userDao.updateUser(user);
//		if(result == 1){
//		return SUCCESS_RESULT;
//		}
//		return FAILURE_RESULT;
//}

	
}
