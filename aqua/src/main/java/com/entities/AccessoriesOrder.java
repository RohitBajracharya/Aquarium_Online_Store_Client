package com.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "accessories_orders")
public class AccessoriesOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_id_seq")
	@SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", initialValue = 10000)
	@Column(name = "order_id")
	private Long orderId;

	@Column(name="username")
    private String username;
    
    @Column(name="product_name")
    private String productName;
    @Column(name = "product_image")
	@Lob
	private byte[] productImage;

	@Column(name = "price")
	private int price;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "total")
	private int total;
	@Column(name="delivered_status")
    private String deliveredStatus;
    @Column(name="checkout_status")
    private String checkoutStatus;
    @Column(name="order_date")
    private Date orderDate;
	public AccessoriesOrder() {
		super();
		// TODO Auto-generated constructor stub
	}



	public AccessoriesOrder(Long orderId, String username, String productName, byte[] productImage, int price,
			int quantity, int total) {
		super();
		this.orderId = orderId;
		this.username = username;
		this.productName = productName;
		this.productImage = productImage;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
	}
	



	public AccessoriesOrder(String username, String productName, byte[] productImage, int price, int quantity) {
		super();
		this.username = username;
		this.productName = productName;
		this.productImage = productImage;
		this.price = price;
		this.quantity = quantity;
	}



	public AccessoriesOrder(String username, String productName, byte[] productImage, int price, int quantity, int total) {
		super();
		this.username = username;
		this.productName = productName;
		this.productImage = productImage;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
	}



	public AccessoriesOrder(String username, String productName, byte[] productImage, int price, int quantity,
			String deliveredStatus, String checkoutStatus) {
		super();
		this.username = username;
		this.productName = productName;
		this.productImage = productImage;
		this.price = price;
		this.quantity = quantity;
		this.deliveredStatus = deliveredStatus;
		this.checkoutStatus = checkoutStatus;
	}



	public AccessoriesOrder(String username, String productName, byte[] productImage, int price, int quantity,
			String deliveredStatus, String checkoutStatus, Date orderDate) {
		super();
		this.username = username;
		this.productName = productName;
		this.productImage = productImage;
		this.price = price;
		this.quantity = quantity;
		this.deliveredStatus = deliveredStatus;
		this.checkoutStatus = checkoutStatus;
		this.orderDate = orderDate;
	}



	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public byte[] getProductImage() {
		return productImage;
	}



	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}



	public String getDeliveredStatus() {
		return deliveredStatus;
	}



	public void setDeliveredStatus(String deliveredStatus) {
		this.deliveredStatus = deliveredStatus;
	}



	public String getCheckoutStatus() {
		return checkoutStatus;
	}



	public void setCheckoutStatus(String checkoutStatus) {
		this.checkoutStatus = checkoutStatus;
	}



	
}
