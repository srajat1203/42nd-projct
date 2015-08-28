package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the USRS database table.
 * 
 */
@Entity
@Table(name="USRS", schema="TESTDB")
@NamedQuery(name="Usr.findAll", query="SELECT u FROM Usr u")
public class Usr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String name;

	public Usr() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// own entries
	
	private String moto;
	
	public String getMoto(){
		return this.moto;
	}

	public void setMoto(String moto){
		this.moto = moto;
	}
	
	private String joindate;
	
	public String getJoindate(){
		return this.joindate;
	}
	
	public void setJoindate(String joindate){
	this.joindate = joindate;
	}
	
	
    private String img;
	
	public String getImg(){
		return this.img;
	}
	
	public void setImg(String img){
		this.img = img;
	}
	
	
	
}