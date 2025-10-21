package model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The primary key class for the savedproduct database table.
 * 
 */
@Embeddable
public class SavedproductPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int user_idUser;

	@Column(insertable=false, updatable=false)
	private int product_idProduct;

	public SavedproductPK() {
	}
	public int getUser_idUser() {
		return this.user_idUser;
	}
	public void setUser_idUser(int user_idUser) {
		this.user_idUser = user_idUser;
	}
	public int getProduct_idProduct() {
		return this.product_idProduct;
	}
	public void setProduct_idProduct(int product_idProduct) {
		this.product_idProduct = product_idProduct;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SavedproductPK)) {
			return false;
		}
		SavedproductPK castOther = (SavedproductPK)other;
		return 
			(this.user_idUser == castOther.user_idUser)
			&& (this.product_idProduct == castOther.product_idProduct);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.user_idUser;
		hash = hash * prime + this.product_idProduct;
		
		return hash;
	}
}