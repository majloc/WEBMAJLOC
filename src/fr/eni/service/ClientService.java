package fr.eni.service;

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

@POST
@Path("/update")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public String updateClient(
		@FormParam("nom")String nom,
		@FormParam("prenom")String prenom,
		@FormParam("adresse")String adresse,
		@FormParam("mail")String mail,
		@FormParam("tel")String tel,
		@FormParam("id") int id,
		@Context HttpServletResponse servletResponse){
	
	String result="";
	Client c = new Client(id,nom,prenom,adresse,mail,tel);
	
	result = ClientDAO.update(c);
	
	return result;
}

}
