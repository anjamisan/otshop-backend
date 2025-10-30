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
}

