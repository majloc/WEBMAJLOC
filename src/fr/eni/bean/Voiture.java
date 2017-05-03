package fr.eni.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VOITURE")
public class Voiture implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double prixParJour;
	private String plaque;
	private String marque;
	private String modele;
	private int nbPlace;
	@ElementCollection(targetClass=String.class)
	private List<String> photos;
	@ManyToOne
	private Type type;
	@ManyToOne
	private Energie energie;
	@ManyToOne
	private Agence agence;
	
	
	
	
	public Voiture() {
		super();
	}
	
	public Voiture(double prixParJour, String plaque, String marque,
			String modele, int nbPlace, List<String> photos, Type type,
			Energie energie, Agence agence) {
		super();
		this.prixParJour = prixParJour;
		this.plaque = plaque;
		this.marque = marque;
		this.modele = modele;
		this.nbPlace = nbPlace;
		this.photos = photos;
		this.type = type;
		this.energie = energie;
		this.agence = agence;
	}
	
	public Voiture(int id, double prixParJour, String plaque, String marque,
			String modele, int nbPlace, List<String> photos, Type type,
			Energie energie, Agence agence) {
		super();
		this.id = id;
		this.prixParJour = prixParJour;
		this.plaque = plaque;
		this.marque = marque;
		this.modele = modele;
		this.nbPlace = nbPlace;
		this.photos = photos;
		this.type = type;
		this.energie = energie;
		this.agence = agence;
	}
	
	public Agence getAgence() {
		return agence;
	}
	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	public double getPrixParJour() {
		return prixParJour;
	}
	public void setPrixParJour(double prixParJour) {
		this.prixParJour = prixParJour;
	}
	public String getPlaque() {
		return plaque;
	}
	public void setPlaque(String plaque) {
		this.plaque = plaque;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public int getNbPlace() {
		return nbPlace;
	}
	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}
	public List<String> getPhotos() {
		return photos;
	}
	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Energie getEnergie() {
		return energie;
	}
	public void setEnergie(Energie energie) {
		this.energie = energie;
	}
	public int getId() {
		return id;
	}
	
	
	
	
}
