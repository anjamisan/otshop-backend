package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the purchase database table.
 * 
 */
@Entity
@NamedQuery(name="Purchase.findAll", query="SELECT p FROM Purchase p")
public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrder;

	private int finalPrice;

	private String status;

	private int user_idUser;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	public Purchase() {
	}

	public int getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getFinalPrice() {
		return this.finalPrice;
	}

	public void setFinalPrice(int finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUser_idUser() {
		return this.user_idUser;
	}

	public void setUser_idUser(int user_idUser) {
		this.user_idUser = user_idUser;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}