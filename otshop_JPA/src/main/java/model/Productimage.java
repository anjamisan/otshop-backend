package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the productimage database table.
 * 
 */
@Entity
@NamedQuery(name="Productimage.findAll", query="SELECT p FROM Productimage p")
public class Productimage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProductImage;

	private String url;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	public Productimage() {
	}

	public int getIdProductImage() {
		return this.idProductImage;
	}

	public void setIdProductImage(int idProductImage) {
		this.idProductImage = idProductImage;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}