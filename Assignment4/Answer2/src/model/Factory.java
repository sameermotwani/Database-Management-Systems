package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the factory database table.
 * 
 */
@Entity
@NamedQuery(name="Factory.findAll", query="SELECT f FROM Factory f")
public class Factory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	//bi-directional many-to-one association to Machine
	@OneToMany(mappedBy="factory")
	private List<Machine> machines;

	public Factory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Machine> getMachines() {
		return this.machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}

	public Machine addMachine(Machine machine) {
		getMachines().add(machine);
		machine.setFactory(this);

		return machine;
	}

	public Machine removeMachine(Machine machine) {
		getMachines().remove(machine);
		machine.setFactory(null);

		return machine;
	}

}