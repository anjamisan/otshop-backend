package model;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name="savedproduct")
@NamedQuery(name="Savedproduct.findAll", query="SELECT s FROM Savedproduct s")
public class Savedproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SavedproductPK id;

	@ManyToOne
	@MapsId("userIdUser")
	@JoinColumn(name = "user_idUser")
	private User user;

	@ManyToOne
	@MapsId("productIdProduct")
	@JoinColumn(name = "product_idProduct")
	private Product product;

	public Savedproduct() {}

	public SavedproductPK getId() {
		return id;
	}

	public void setId(SavedproductPK id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
