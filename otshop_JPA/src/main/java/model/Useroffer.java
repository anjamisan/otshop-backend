package model;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

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

	@ManyToOne
	@JoinColumn(name = "user_idUser")
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_idProduct")
	private Product product;

	public Useroffer() {}

	public int getIdUserOffer() {
		return idUserOffer;
	}

	public void setIdUserOffer(int idUserOffer) {
		this.idUserOffer = idUserOffer;
	}

	public int getOfferedPrice() {
		return offeredPrice;
	}

	public void setOfferedPrice(int offeredPrice) {
		this.offeredPrice = offeredPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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
