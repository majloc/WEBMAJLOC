package fr.eni.main;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import fr.eni.bean.Voiture;


public class WebServiceTester {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/WEBMAJLOC/rest/voiture/allcars";
	private static final String SUCCES_RESULT = "<result>success</result>";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";
	
	private void init(){
	this.client = ClientBuilder.newClient();
	}
	
	
	public static void main(String[] args) {
		
		WebServiceTester tester = new WebServiceTester();
		//initialiser le tester
		tester.init();
		
		//test de la methode getall
		tester.testGetAllVoitures();

		
	}

	private void testGetAllVoitures(){
		GenericType<List<Voiture>> list = new GenericType<List<Voiture>>() {};
		List<Voiture> voitures = client
		.target(REST_SERVICE_URL)
		.request(MediaType.APPLICATION_XML)
		.get(list);
		String result = PASS;
		if(voitures.isEmpty()){
		result = FAIL;
		}
		System .out.println("Test case name: testGetAllVoitures, Result: " + result );
		}
}
