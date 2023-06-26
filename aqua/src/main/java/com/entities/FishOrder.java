package com.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "fish_orders")
public class FishOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "username")
	private String username;

	@Column(name = "fish_name")
	private String fishName;
	@Column(name = "fish_image")
	@Lob
	private byte[] fishImage;

	@Column(name = "price")
	private int price;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "total")
	private int total;
	@Column(name = "delivered_status")
	private String deliveredStatus;
	@Column(name = "checkout_status") 
	private String checkoutStatus;
	@Column(name="order_date")
	private Date orderDate;

	public FishOrder() {
		super();
	}

	public FishOrder(Long orderId, String username, String fishName, byte[] fishImage, int price, int quantity,
			int total) {
		super();
		this.orderId = orderId;
		this.username = username;
		this.fishName = fishName;
		this.fishImage = fishImage;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
	}

	public FishOrder(String username, String fishName, byte[] fishImage, int price, int quantity) {
		super();
		this.username = username;
		this.fishName = fishName;
		this.fishImage = fishImage;
		this.price = price;
		this.quantity = quantity;
	}

	public FishOrder(String username, String fishName, byte[] fishImage, int price, int quantity, int total) {
		super();
		this.username = username;
		this.fishName = fishName;
		this.fishImage = fishImage;
		this.price = price;
		this.quantity = quantity;
		this.total = total;
	}
	

	public FishOrder(String username, String fishName, byte[] fishImage, int price, int quantity,
			String deliveredStatus, String checkoutStatus) {
		super();
		this.username = username;
		this.fishName = fishName;
		this.fishImage = fishImage;
		this.price = price;
		this.quantity = quantity;
		this.deliveredStatus = deliveredStatus;
		this.checkoutStatus = checkoutStatus;
	}

	public FishOrder(String username, String fishName, byte[] fishImage, int price, int quantity,
			String deliveredStatus, String checkoutStatus, Date orderDate) {
		super();
		this.username = username;
		this.fishName = fishName;
		this.fishImage = fishImage;
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

	public String getFishName() {
		return fishName;
	}

	public void setFishName(String fishName) {
		this.fishName = fishName;
	}

	public byte[] getFishImage() {
		return fishImage;
	}

	public void setFishImage(byte[] fishImage) {
		this.fishImage = fishImage;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

}
