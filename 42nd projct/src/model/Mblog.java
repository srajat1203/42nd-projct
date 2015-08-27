package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MBLOGS database table.
 * 
 */
@Entity
@Table(name="MBLOGS", schema="TESTDB")
@NamedQuery(name="Mblog.findAll", query="SELECT m FROM Mblog m")
public class Mblog implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@SequenceGenerator (name = "MblogSeq", sequenceName = "MBLOGS_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "MblogSeq")
	private long id;

	private String post;

	public Mblog() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

}