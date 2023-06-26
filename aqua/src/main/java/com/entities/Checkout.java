package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checkout_details")
public class Checkout {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkout_id")
    private int checkoutId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "grand_total")
    private double grandTotal;
    
    @Column(name="payment_method")
    private String paymentMethod;
    
 

	public Checkout() {
		super();
	
	}

	public Checkout(int checkoutId, Customer customer, String customerName, double grandTotal) {
		super();
		this.checkoutId = checkoutId;
		this.customer = customer;
		this.customerName = customerName;
		this.grandTotal = grandTotal;
	}

	public Checkout(Customer customer, String customerName, double grandTotal) {
		super();
		this.customer = customer;
		this.customerName = customerName;
		this.grandTotal = grandTotal;
	}

	public int getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(int checkoutId) {
		this.checkoutId = checkoutId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
    

}
