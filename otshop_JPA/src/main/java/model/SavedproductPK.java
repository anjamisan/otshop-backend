package model;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class SavedproductPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="user_idUser")
	private int userIdUser;

	@Column(name="product_idProduct")
	private int productIdProduct;

	public SavedproductPK() {}

	public int getUserIdUser() {
		return userIdUser;
	}

	public void setUserIdUser(int userIdUser) {
		this.userIdUser = userIdUser;
	}

	public int getProductIdProduct() {
		return productIdProduct;
	}

	public void setProductIdProduct(int productIdProduct) {
		this.productIdProduct = productIdProduct;
	}
	
	
}

