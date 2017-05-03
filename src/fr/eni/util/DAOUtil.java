package fr.eni.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOUtil {

		private static EntityManagerFactory emf;
		private static EntityManager em;
		
		static {
			emf = Persistence.createEntityManagerFactory("MAJLOCWEBSERVICE");
			em = emf.createEntityManager();
		}
		
		
		public static EntityManager getEntityManager(){
			return em;
		}
		
		public static void close(){
			emf.close();
		}	
		
}
