package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the productprocessor database table.
 * 
 */
@Entity
@NamedQuery(name="Productprocessor.findAll", query="SELECT p FROM Productprocessor p")
public class Productprocessor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int processes;

	//bi-directional one-to-one association to Product
	@OneToOne
	@JoinColumn(name="processes")
	private Product product;

	//bi-directional many-to-one association to Machine
	@ManyToOne
	@JoinColumn(name="processedBy")
	private Machine machine;

	public Productprocessor() {
	}

	public int getProcesses() {
		return this.processes;
	}

	public void setProcesses(int processes) {
		this.processes = processes;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Machine getMachine() {
		return this.machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

}