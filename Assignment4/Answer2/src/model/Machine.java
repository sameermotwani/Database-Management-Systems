package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the machine database table.
 * 
 */
@Entity
@NamedQuery(name="Machine.findAll", query="SELECT m FROM Machine m")
public class Machine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String code;

	//bi-directional many-to-one association to Factory
	@ManyToOne
	@JoinColumn(name="partOf")
	private Factory factory;

	//bi-directional many-to-one association to Productprocessor
	@OneToMany(mappedBy="machine")
	private List<Productprocessor> productprocessors;

	public Machine() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Factory getFactory() {
		return this.factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}

	public List<Productprocessor> getProductprocessors() {
		return this.productprocessors;
	}

	public void setProductprocessors(List<Productprocessor> productprocessors) {
		this.productprocessors = productprocessors;
	}

	public Productprocessor addProductprocessor(Productprocessor productprocessor) {
		getProductprocessors().add(productprocessor);
		productprocessor.setMachine(this);

		return productprocessor;
	}

	public Productprocessor removeProductprocessor(Productprocessor productprocessor) {
		getProductprocessors().remove(productprocessor);
		productprocessor.setMachine(null);

		return productprocessor;
	}

}