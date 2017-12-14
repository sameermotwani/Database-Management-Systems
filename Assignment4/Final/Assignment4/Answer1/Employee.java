package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(length=200, nullable=false)
	private String name;

	//bi-directional many-to-one association to Qualification
	@OneToMany(mappedBy="employee")
	private List<Qualification> qualifications;

	public Employee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Qualification> getQualifications() {
		return this.qualifications;
	}

	public void setQualifications(List<Qualification> qualifications) {
		this.qualifications = qualifications;
	}

	public Qualification addQualification(Qualification qualification) {
		getQualifications().add(qualification);
		qualification.setEmployee(this);

		return qualification;
	}

	public Qualification removeQualification(Qualification qualification) {
		getQualifications().remove(qualification);
		qualification.setEmployee(null);

		return qualification;
	}

}