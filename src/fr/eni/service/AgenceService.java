package fr.eni.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.eni.bean.Agence;
import fr.eni.dao.AgenceDAO;
import fr.eni.dao.ClientDAO;

@Path("/agence")
public class AgenceService {

	
	@GET
	@Path("/loginmdp")
	@Produces(MediaType.APPLICATION_JSON)
	public Agence getAgenceByLogMdp(@QueryParam("logmdp") String identification){
		return AgenceDAO.findAgenceByLoginMdp(identification);
	}
	
	
	
	
	
	
}
