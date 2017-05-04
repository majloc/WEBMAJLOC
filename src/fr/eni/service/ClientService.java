package fr.eni.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.eni.bean.Client;
import fr.eni.bean.Voiture;
import fr.eni.dao.ClientDAO;
import fr.eni.dao.VoitureDAO;

@Path("/client")
public class ClientService {

	
@GET
@Path("/allclients")
@Produces(MediaType.APPLICATION_JSON)
public List<Client> getall(){
	return ClientDAO.findAll();
}
	
@GET
@Path("/clientbyfiltres")
@Produces(MediaType.APPLICATION_JSON)
public List<Client> getAllByCritere(@QueryParam("filtres") String filtres){
	return ClientDAO.findAllByFiltres(filtres);
}

	
}
