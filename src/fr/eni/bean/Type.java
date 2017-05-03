package fr.eni.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="TYPE")
public class Type implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private EnumType type;
	
	
	
	
	public Type() {
		super();
	}

	public Type(EnumType type) {
		super();
		this.type = type;
	}

	public Type(int id, EnumType type) {
		super();
		this.id = id;
		this.type = type;
	}
	
	public EnumType getType() {
		return type;
	}
	
	public void setType(EnumType type) {
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	
	
}
