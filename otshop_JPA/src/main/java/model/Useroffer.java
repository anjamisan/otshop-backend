package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the useroffer database table.
 * 
 */
@Entity
@NamedQuery(name="Useroffer.findAll", query="SELECT u FROM Useroffer u")
public class Useroffer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUserOffer;

	private int offeredPrice;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	private int user_idUser;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	public Useroffer() {
	}

	public int getIdUserOffer() {
		return this.idUserOffer;
	}

	public void setIdUserOffer(int idUserOffer) {
		this.idUserOffer = idUserOffer;
	}

	public int getOfferedPrice() {
		return this.offeredPrice;
	}

	public void setOfferedPrice(int offeredPrice) {
		this.offeredPrice = offeredPrice;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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