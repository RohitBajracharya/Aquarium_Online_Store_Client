package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "fish_details")
public class Fish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fish_Id")
	private int fishId;
	@Column(name = "fish_type", length = 100)
	private String fishType;
	@Column(name = "fish_name", length = 100)
	private String fishName;
	@Column(name = "fish_price", length = 100)
	private String fishPrice;
	@Column(name = "fish_stock", length = 100)
	private String fishStock;
	@Column(name = "fish_unit", length = 100)
	private String fishUnit;

	@Column(name = "fish_image")
	@Lob
	private byte[] fishImage;
	

	public Fish() {
		super();
	}
	/*
	 * public Fish(String fishType, String fishName, String fishPrice, String
	 * fishStock, String fishUnit, byte[] fishImage, int fishSalesCount) { super();
	 * this.fishType = fishType; this.fishName = fishName; this.fishPrice =
	 * fishPrice; this.fishStock = fishStock; this.fishUnit = fishUnit;
	 * this.fishImage = fishImage; this.fishSalesCount = fishSalesCount; }
	 * 
	 * public Fish(int fishId, String fishType, String fishName, String fishPrice,
	 * String fishStock, String fishUnit, byte[] fishImage, int fishSalesCount) {
	 * super(); this.fishId = fishId; this.fishType = fishType; this.fishName =
	 * fishName; this.fishPrice = fishPrice; this.fishStock = fishStock;
	 * this.fishUnit = fishUnit; this.fishImage = fishImage; this.fishSalesCount =
	 * fishSalesCount; }
	 */

	public Fish(int fishId, String fishType, String fishName, String fishPrice, String fishStock, String fishUnit, byte[] fishImage) {
		super();
		this.fishId = fishId;
		this.fishType = fishType;
		this.fishName = fishName;
		this.fishPrice = fishPrice;

		this.fishStock = fishStock;
		this.fishUnit = fishUnit;
		this.fishImage = fishImage;
	}

	public Fish(String fishType, String fishName, String fishPrice, String fishStock, String fishUnit, byte[] fishImage) {
		super();
		this.fishType = fishType;
		this.fishName = fishName;
		this.fishPrice = fishPrice;
		this.fishStock = fishStock;
		this.fishUnit = fishUnit;
		this.fishImage = fishImage;
	}

	public int getFishId() {
		return fishId;
	}

	public void setFishId(int fishId) {
		this.fishId = fishId;
	}

	public String getFishType() {
		return fishType;
	}

	public void setFishType(String fishType) {
		this.fishType = fishType;
	}

	public String getFishName() {
		return fishName;
	}

	public void setFishName(String fishName) {
		this.fishName = fishName;
	}

	public String getFishPrice() {
		return fishPrice;
	}

	public void setFishPrice(String fishPrice) {
		this.fishPrice = fishPrice;
	}

	public String getFishStock() {
		return fishStock;
	}

	public void setFishStock(String fishStock) {
		this.fishStock = fishStock;
	}

	public String getFishUnit() {
		return fishUnit;
	}

	public void setFishUnit(String fishUnit) {
		this.fishUnit = fishUnit;
	}
	public byte[] getFishImage() {
		return fishImage;
	}

	public void setFishImage(byte[] fishImage) {
		this.fishImage = fishImage;
	}



}
