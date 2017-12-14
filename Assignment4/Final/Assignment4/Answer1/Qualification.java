package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the qualification database table.
 * 
 */
@Entity
@NamedQuery(name="Qualification.findAll", query="SELECT q FROM Qualification q")
public class Qualification implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QualificationPK id;

	private String level;

	//bi-directional many-to-one association to Producttype
	@ManyToOne
	@JoinColumn(name="canRepair")
	private Producttype producttype;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="canBeRepairedBy")
	private Employee employee;

	public Qualification() {
	}

	public QualificationPK getId() {
		return this.id;
	}

	public void setId(QualificationPK id) {
		this.id = id;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Producttype getProducttype() {
		return this.producttype;
	}

	public void setProducttype(Producttype producttype) {
		this.producttype = producttype;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}