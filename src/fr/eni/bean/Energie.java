package fr.eni.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.metamodel.StaticMetamodel;

@Entity
@Table(name="ENERGIE")
public class Energie implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private EnumEnergie energie;
	
	
	
	
	
	public Energie() {
		super();
	}

	public Energie(EnumEnergie energie) {
		super();
		this.energie = energie;
	}

	public Energie(int id, EnumEnergie energie) {
		super();
		this.id = id;
		this.energie = energie;
	}
	
	public EnumEnergie getEnergie() {
		return energie;
	}
	
	public void setEnergie(EnumEnergie energie) {
		this.energie = energie;
	}
	
	public int getId() {
		return id;
	}
	
	
	
}
