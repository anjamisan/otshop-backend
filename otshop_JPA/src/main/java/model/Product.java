package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProduct;

	private String condition;

	private String description;

	private int price;

	private String productName;

	private byte reserved;

	//bi-directional many-to-one association to Agesex
	@ManyToOne
	private Agesex agesex;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	//bi-directional many-to-one association to Productimage
	@OneToMany(mappedBy="product")
	private List<Productimage> productimages;

	//bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy="product")
	private List<Purchase> purchases;

	//bi-directional many-to-one association to Savedproduct
	@OneToMany(mappedBy="product")
	private List<Savedproduct> savedproducts;

	//bi-directional many-to-one association to Useroffer
	@OneToMany(mappedBy="product")
	private List<Useroffer> useroffers;

	public Product() {
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public byte getReserved() {
		return this.reserved;
	}

	public void setReserved(byte reserved) {
		this.reserved = reserved;
	}

	public Agesex getAgesex() {
		return this.agesex;
	}

	public void setAgesex(Agesex agesex) {
		this.agesex = agesex;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Productimage> getProductimages() {
		return this.productimages;
	}

	public void setProductimages(List<Productimage> productimages) {
		this.productimages = productimages;
	}

	public Productimage addProductimage(Productimage productimage) {
		getProductimages().add(productimage);
		productimage.setProduct(this);

		return productimage;
	}

	public Productimage removeProductimage(Productimage productimage) {
		getProductimages().remove(productimage);
		productimage.setProduct(null);

		return productimage;
	}

	public List<Purchase> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Purchase addPurchas(Purchase purchas) {
		getPurchases().add(purchas);
		purchas.setProduct(this);

		return purchas;
	}

	public Purchase removePurchas(Purchase purchas) {
		getPurchases().remove(purchas);
		purchas.setProduct(null);

		return purchas;
	}

	public List<Savedproduct> getSavedproducts() {
		return this.savedproducts;
	}

	public void setSavedproducts(List<Savedproduct> savedproducts) {
		this.savedproducts = savedproducts;
	}

	public Savedproduct addSavedproduct(Savedproduct savedproduct) {
		getSavedproducts().add(savedproduct);
		savedproduct.setProduct(this);

		return savedproduct;
	}

	public Savedproduct removeSavedproduct(Savedproduct savedproduct) {
		getSavedproducts().remove(savedproduct);
		savedproduct.setProduct(null);

		return savedproduct;
	}

	public List<Useroffer> getUseroffers() {
		return this.useroffers;
	}

	public void setUseroffers(List<Useroffer> useroffers) {
		this.useroffers = useroffers;
	}

	public Useroffer addUseroffer(Useroffer useroffer) {
		getUseroffers().add(useroffer);
		useroffer.setProduct(this);

		return useroffer;
	}

	public Useroffer removeUseroffer(Useroffer useroffer) {
		getUseroffers().remove(useroffer);
		useroffer.setProduct(null);

		return useroffer;
	}

}