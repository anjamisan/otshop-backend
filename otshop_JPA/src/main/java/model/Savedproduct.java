package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the savedproduct database table.
 * 
 */
@Entity
@NamedQuery(name="Savedproduct.findAll", query="SELECT s FROM Savedproduct s")
public class Savedproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SavedproductPK id;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	public Savedproduct() {
	}

	public SavedproductPK getId() {
		return this.id;
	}

	public void setId(SavedproductPK id) {
		this.id = id;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}