package com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.entities.FishOrder;

public class FishOrderDao {
	private HibernateTemplate hibernateTemplate;

	// insert data
	@Transactional
	public Long insert(FishOrder fishOrder) {
		int price = fishOrder.getPrice();
		int quantity = fishOrder.getQuantity();
		int total = price * quantity;
		fishOrder.setTotal(total);
		Long id = (Long) this.hibernateTemplate.save(fishOrder);
		return id;
	}

	// deleting the data
	@Transactional
	public void deleteOrder(Long fishOrderId) {
		FishOrder order = this.hibernateTemplate.get(FishOrder.class, fishOrderId);
		this.hibernateTemplate.delete(order);
	}

	// updating the data
	@Transactional
	public void updateOrder(FishOrder fishOrder) {
		this.hibernateTemplate.update(fishOrder);
	}

	@Transactional
	public void updateProductQuantity(Long productId, int newQuantity) {

		FishOrder fishorder = hibernateTemplate.get(FishOrder.class, productId);
		int price = fishorder.getPrice();
		int total = price * newQuantity;
		fishorder.setTotal(total);
		fishorder.setQuantity(newQuantity);

		hibernateTemplate.update(fishorder);
	}

	// retrieve all data
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public List<FishOrder> getAllFishOrdersByUsername(String username) {

		List<FishOrder> orders = (List<FishOrder>) hibernateTemplate
				.findByNamedParam("FROM FishOrder WHERE username = :username", "username", username);
		for (FishOrder order : orders) {
			System.out.println(order.toString());
		}
		return orders;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public List<FishOrder> getFishNameAndQuantityByUsername(String username) {
	    List<FishOrder> resultList = (List<FishOrder>) hibernateTemplate.findByNamedParam(
	        "FROM FishOrder fo WHERE fo.username = :username AND fo.checkoutStatus = 'Not Checkout'",
	        new String[] { "username" },
	        new Object[] { username });

	    return resultList;
	}



	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<FishOrder> getFishOrdersByUsernameAndCheckoutStatus(String username, String checkoutStatus) {
	    String query = "FROM FishOrder fo WHERE fo.username = :username AND fo.checkoutStatus = :checkoutStatus";
	    return (List<FishOrder>) hibernateTemplate.findByNamedParam(query, new String[]{"username", "checkoutStatus"}, new Object[]{username, checkoutStatus});
	}


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
