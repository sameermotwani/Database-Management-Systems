package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date completed;

	private String label;

	@Temporal(TemporalType.DATE)
	private Date started;

	//bi-directional many-to-one association to Producttype
	@ManyToOne
	@JoinColumn(name="type")
	private Producttype producttype;

	//bi-directional one-to-one association to Productprocessor
	@OneToOne(mappedBy="product")
	private Productprocessor productprocessor;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCompleted() {
		return this.completed;
	}

	public void setCompleted(Date completed) {
		this.completed = completed;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getStarted() {
		return this.started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Producttype getProducttype() {
		return this.producttype;
	}

	public void setProducttype(Producttype producttype) {
		this.producttype = producttype;
	}

	public Productprocessor getProductprocessor() {
		return this.productprocessor;
	}

	public void setProductprocessor(Productprocessor productprocessor) {
		this.productprocessor = productprocessor;
	}

}