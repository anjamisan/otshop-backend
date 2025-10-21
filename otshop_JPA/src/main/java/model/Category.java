package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCategory;

	private String categoryName;

	//bi-directional many-to-many association to Agesex
	@ManyToMany
	@JoinTable(
		name="agesex_has_category"
		, joinColumns={
			@JoinColumn(name="Category_idCategory")
			}
		, inverseJoinColumns={
			@JoinColumn(name="AgeSex_idAgeSex")
			}
		)
	private List<Agesex> agesexs;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="category")
	private List<Product> products;

	public Category() {
	}

	public int getIdCategory() {
		return this.idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Agesex> getAgesexs() {
		return this.agesexs;
	}

	public void setAgesexs(List<Agesex> agesexs) {
		this.agesexs = agesexs;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}

}