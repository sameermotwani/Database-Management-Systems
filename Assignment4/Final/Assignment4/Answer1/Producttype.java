package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the producttype database table.
 * 
 */
@Entity
@NamedQuery(name="Producttype.findAll", query="SELECT p FROM Producttype p")
public class Producttype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(length=2000, nullable=false)
	private String description;
    
	@Column(length=11, nullable=false)
	private int size;
    
	 @Column(length=200, nullable=true)
	private String title;
   
	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="producttype")
	private List<Product> products;

	//bi-directional many-to-one association to Qualification
	@OneToMany(mappedBy="producttype")
	private List<Qualification> qualifications;

	public Producttype() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProducttype(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProducttype(null);

		return product;
	}

	public List<Qualification> getQualifications() {
		return this.qualifications;
	}

	public void setQualifications(List<Qualification> qualifications) {
		this.qualifications = qualifications;
	}

	public Qualification addQualification(Qualification qualification) {
		getQualifications().add(qualification);
		qualification.setProducttype(this);

		return qualification;
	}

	public Qualification removeQualification(Qualification qualification) {
		getQualifications().remove(qualification);
		qualification.setProducttype(null);

		return qualification;
	}

}