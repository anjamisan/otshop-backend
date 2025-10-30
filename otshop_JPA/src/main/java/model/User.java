package model;



import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "`user`")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUser;

	private String username;
	
	private String email;

	private String password;

	private String fullName;
	
	private boolean isAdmin;

	@OneToMany(mappedBy="user")
	private List<Savedproduct> savedProducts;

	@OneToMany(mappedBy="user")
	private List<Purchase> purchases;

	@OneToMany(mappedBy="user")
	private List<Useroffer> userOffers;

	public User() {}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Savedproduct> getSavedProducts() {
		return savedProducts;
	}

	public void setSavedProducts(List<Savedproduct> savedProducts) {
		this.savedProducts = savedProducts;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public List<Useroffer> getUserOffers() {
		return userOffers;
	}

	public void setUserOffers(List<Useroffer> userOffers) {
		this.userOffers = userOffers;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	
}
