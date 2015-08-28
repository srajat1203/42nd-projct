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

	public Usr() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}