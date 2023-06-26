package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.entities.AccessoriesOrder;
import com.entities.FishOrder;

@SuppressWarnings("deprecation")
public class AccessoriesOrderDao {
	private HibernateTemplate hibernateTemplate;

	// insert data
	@Transactional
	public Long insert(AccessoriesOrder accessoriesOrder) {
		int price = accessoriesOrder.getPrice();
		int quantity = accessoriesOrder.getQuantity();
		int total = price * quantity;
		accessoriesOrder.setTotal(total);
		Long id = (Long) this.hibernateTemplate.save(accessoriesOrder);
		return id;
	}

	// deleting the data
	@Transactional
	public void deleteOrder(Long accesoriesOrderId) {
		AccessoriesOrder order = this.hibernateTemplate.get(AccessoriesOrder.class, accesoriesOrderId);
		this.hibernateTemplate.delete(order);
	}

	// updating the data
	@Transactional
	public void updateOrder(AccessoriesOrder accessoriesOrder) {
		this.hibernateTemplate.update(accessoriesOrder);
	}

	@Transactional
	public void updateProductQuantity(Long productId, int newQuantity) {

		AccessoriesOrder accessoriesOrder = hibernateTemplate.get(AccessoriesOrder.class, productId);
		int price = accessoriesOrder.getPrice();
		int total = price * newQuantity;
		accessoriesOrder.setTotal(total);
		accessoriesOrder.setQuantity(newQuantity);

		hibernateTemplate.update(accessoriesOrder);
	}

	// retrieve all data
	@SuppressWarnings({ "unchecked" })
	@Transactional
	public List<AccessoriesOrder> getAllAccessoriesOrdersByUsername(String username) {

		List<AccessoriesOrder> orders = (List<AccessoriesOrder>) hibernateTemplate
				.findByNamedParam("FROM AccessoriesOrder WHERE username = :username", "username", username);
		for (AccessoriesOrder order : orders) {
			System.out.println(order.toString());
		}
		return orders;
	}

	/*
	 * @SuppressWarnings({ "unchecked", "deprecation" })
	 * 
	 * @Transactional public List<Object[]>
	 * getAccessoriesNameAndQuantityByUsername(String username) { List<Object[]>
	 * resultList = (List<Object[]>) hibernateTemplate.findByNamedParam(
	 * "SELECT fo.productName, fo.quantity FROM AccessoriesOrder fo WHERE fo.username = :username"
	 * , "username", username );
	 * 
	 * return resultList; }
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public List<AccessoriesOrder> getAccessoriesNameAndQuantityByUsername(String username) {
	    List<AccessoriesOrder> resultList = (List<AccessoriesOrder>) hibernateTemplate.findByNamedParam(
	        "FROM AccessoriesOrder fo WHERE fo.username = :username AND fo.checkoutStatus = 'Not Checkout'",
	        new String[] { "username" },
	        new Object[] { username });

	    return resultList;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public List<AccessoriesOrder> getAccessoriesOrdersByUsernameAndCheckoutStatus(String username,
			String checkoutStatus) {
		String query = "FROM AccessoriesOrder ao WHERE ao.username = :username AND ao.checkoutStatus = :checkoutStatus";
		return (List<AccessoriesOrder>) hibernateTemplate.findByNamedParam(query,
				new String[] { "username", "checkoutStatus" }, new Object[] { username, checkoutStatus });
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
