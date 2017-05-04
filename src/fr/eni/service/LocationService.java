package fr.eni.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.eni.bean.Client;
import fr.eni.bean.Location;
import fr.eni.bean.Voiture;
import fr.eni.dao.ClientDAO;
import fr.eni.dao.LocationDAO;
import fr.eni.dao.VoitureDAO;

@Path("/location")
public class LocationService {

	
@GET
@Path("/all")
@Produces(MediaType.APPLICATION_JSON)
public List<Location> getall(){
	return LocationDAO.findAll();
}
	
@GET
@Path("/locationbyfiltres")
@Produces(MediaType.APPLICATION_JSON)
public List<Location> getAllByCritere(@QueryParam("filtres") String filtres){
	return LocationDAO.findAllByFiltres(filtres);
}

	
}
