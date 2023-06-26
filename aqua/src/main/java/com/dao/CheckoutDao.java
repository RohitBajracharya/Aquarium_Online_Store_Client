package com.dao;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.entities.Accessories;
import com.entities.AccessoriesOrder;
import com.entities.Checkout;
import com.entities.Fish;
import com.entities.FishOrder;

@SuppressWarnings("deprecation")
public class CheckoutDao {
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public int insert(Checkout checkout, String username) {
		updateFishStock(checkout.getCustomerName());
		updateAccessoriesStock(checkout.getCustomerName());
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		FishOrderDao fishOrderDao = context.getBean("fishOrderDao", FishOrderDao.class);
		List<FishOrder> fishOrders = fishOrderDao.getFishOrdersByUsernameAndCheckoutStatus(username, "Not Checkout");

		for (FishOrder fishOrder : fishOrders) {
			fishOrder.setCheckoutStatus("Checkout");
			fishOrderDao.updateOrder(fishOrder);
		}

		AccessoriesOrderDao accessoriesOrderDao = context.getBean("accessoriesOrderDao", AccessoriesOrderDao.class);
		List<AccessoriesOrder> accessoriesOrders = accessoriesOrderDao
				.getAccessoriesOrdersByUsernameAndCheckoutStatus(username, "Not Checkout");
		
		for (AccessoriesOrder accessoriesOrder : accessoriesOrders) {
			accessoriesOrder.setCheckoutStatus("Checkout");
			accessoriesOrderDao.updateOrder(accessoriesOrder);
		}
		
		
		Integer i = (Integer) this.hibernateTemplate.save(checkout);
		return i;
	}

	@Transactional
	public void updateFishStock(String customerName) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		FishOrderDao fishOrderDao = context.getBean("fishOrderDao", FishOrderDao.class);
		List<FishOrder> fishOrderList = fishOrderDao.getFishNameAndQuantityByUsername(customerName);

		for (FishOrder fishOrder : fishOrderList) {
			final String fishName = fishOrder.getFishName();
			final int fishOrderQuantity = fishOrder.getQuantity();
			System.out.println("fish order stock : " + fishOrderQuantity);

			getHibernateTemplate().execute(new HibernateCallback<Void>() {
				public Void doInHibernate(Session session) throws HibernateException {
					Query<Fish> query = session.createQuery("from Fish f where f.fishName = :fishName", Fish.class);
					query.setParameter("fishName", fishName);
					query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
					Fish fish = query.uniqueResult();

					int fishStock = Integer.parseInt(fish.getFishStock());
					System.out.println("fish stock : " + fishStock);
					int remainingFishStock = fishStock - fishOrderQuantity;
					System.out.println("remaining fish stock: " + remainingFishStock);

					if (remainingFishStock <= 0) {
						fish.setFishStock(Integer.toString(0));
					} else {
						fish.setFishStock(Integer.toString(remainingFishStock));
					}

					session.update(fish);
					return null;
				}
			});
		}

		((ClassPathXmlApplicationContext) context).close();
	}

	@Transactional
	public void updateAccessoriesStock(String customerName) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		AccessoriesOrderDao accessoriesOrderDao = context.getBean("accessoriesOrderDao", AccessoriesOrderDao.class);
		List<AccessoriesOrder> accessoriesOrderList = accessoriesOrderDao.getAccessoriesNameAndQuantityByUsername(customerName);

		for (AccessoriesOrder accessoriesOrder : accessoriesOrderList) {
			final String productName = accessoriesOrder.getProductName();
			final int accessoriesOrderQuantity = accessoriesOrder.getQuantity();

			getHibernateTemplate().execute(new HibernateCallback<Void>() {
				public Void doInHibernate(Session session) throws HibernateException {
					Query<Accessories> query = session
							.createQuery("from Accessories a where a.productName = :productName", Accessories.class);
					query.setParameter("productName", productName);
					query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
					Accessories accessories = query.uniqueResult();

					int accessoriesStock = Integer.parseInt(accessories.getProductStock());
					int remainingAccessoriesStock = accessoriesStock - accessoriesOrderQuantity;
					System.out.println("remaining accessories stock: " + remainingAccessoriesStock);
					if (remainingAccessoriesStock <= 0) {
						accessories.setProductStock(Integer.toString(0));
					} else {
						accessories.setProductStock(Integer.toString(remainingAccessoriesStock));

					}
					session.update(accessories);
					return null;
				}
			});
		}
		((ClassPathXmlApplicationContext) context).close();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Checkout> getCheckoutDetailsByUsername(String username) {
	    String queryString = "FROM Checkout cd WHERE cd.username = :username";
	    List<Checkout> checkoutDetails = (List<Checkout>) hibernateTemplate.findByNamedParam(queryString, "username", username);
	    
	    return checkoutDetails;
	}


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
