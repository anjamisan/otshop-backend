package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the agesex database table.
 * 
 */
@Entity
@NamedQuery(name="Agesex.findAll", query="SELECT a FROM Agesex a")
public class Agesex implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAgeSex;

	private String ageSexGroup;

	//bi-directional many-to-many association to Category
	@ManyToMany(mappedBy="agesexs")
	private List<Category> categories;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="agesex")
	private List<Product> products;

	public Agesex() {
	}

	public int getIdAgeSex() {
		return this.idAgeSex;
	}

	public void setIdAgeSex(int idAgeSex) {
		this.idAgeSex = idAgeSex;
	}

	public String getAgeSexGroup() {
		return this.ageSexGroup;
	}

	public void setAgeSexGroup(String ageSexGroup) {
		this.ageSexGroup = ageSexGroup;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setAgesex(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setAgesex(null);

		return product;
	}

}