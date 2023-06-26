package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "accessories_details")
public class Accessories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_Id")
	private int productId;
	@Column(name = "product_category", length = 100)
	private String productCategory;
	@Column(name = "product_name", length = 100)
	private String productName;
	@Column(name = "product_price", length = 100)
	private String productPrice;
	@Column(name = "productStock", length = 100)
	private String productStock;
	@Column(name="product_unit",length=100)
	private String productUnit;
	@Column(name = "product_image")
	@Lob
	private byte[] productImage;

	public Accessories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Accessories(String productCategory, String productName, String productPrice, String productStock,String productUnit,
			byte[] productImage) {
		super();
		this.productCategory = productCategory;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productUnit=productUnit;
		this.productImage = productImage;
		
	}

	public Accessories(int productId, String productCategory, String productName, String productPrice,
			String productStock,String productUnit, byte[] productImage) {
		super();
		this.productId = productId;
		this.productCategory = productCategory;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.productUnit=productUnit;
		this.productImage = productImage;
		
	}

	/*
	 * public Accessories(int productId, String productCategory, String productName,
	 * String productPrice, String productStock,String productUnit, byte[]
	 * productImage) { super(); this.productId = productId; this.productCategory =
	 * productCategory; this.productName = productName; this.productPrice =
	 * productPrice; this.productStock = productStock; this.productUnit=productUnit;
	 * this.productImage = productImage; }
	 * 
	 * public Accessories(String productCategory, String productName, String
	 * productPrice, String productStock,String productUnit, byte[] productImage) {
	 * super(); this.productCategory = productCategory; this.productName =
	 * productName; this.productPrice = productPrice; this.productStock =
	 * productStock; this.productUnit=productUnit; this.productImage = productImage;
	 * }
	 */
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductStock() {
		return productStock;
	}

	public void setProductStock(String productStock) {
		this.productStock = productStock;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}
}
