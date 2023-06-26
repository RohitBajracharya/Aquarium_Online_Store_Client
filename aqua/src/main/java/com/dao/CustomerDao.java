package com.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.entities.Customer;

public class CustomerDao {
	private HibernateTemplate hibernateTemplate;

	// insert data
	@Transactional
	public int insert(Customer customer) {
		Integer i = (Integer) this.hibernateTemplate.save(customer);
		return i;
	}

	// deleting the data
	@Transactional
	public void deleteCustomer(int userId) {
		Customer customer = this.hibernateTemplate.get(Customer.class, userId);
		this.hibernateTemplate.delete(customer);
	}

	// updating the data
	@Transactional
	public void updateCustomer(Customer customer) {
		this.hibernateTemplate.update(customer);
	}

	// retrieve all data
	public List<Customer> getAllCustomers() {
		List<Customer> customers = this.hibernateTemplate.loadAll(Customer.class);
		return customers;
	}

	// get single data(object)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public int getCustomer(Customer customer) {
		String queryString = "FROM Customer WHERE username = :username AND password = :password";
		Query query = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(queryString);
		query.setParameter("username", customer.getUsername());
		query.setParameter("password", customer.getPassword());
		List<Customer> customerList = (List<Customer>) ((org.hibernate.query.Query) query).list();
		if (customerList != null && !customerList.isEmpty()) {
			return 1;
		}
		return 0;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	public int getCustomerIdByUsername(String username) {
	    DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class)
	            .add(Restrictions.eq("username", username))
	            .setProjection(Projections.property("customerId"));
	    List<Integer> customerIdList = (List<Integer>) hibernateTemplate.findByCriteria(criteria);
	    if (customerIdList.isEmpty()) {
	        return -1; // or any other appropriate error code
	    }
	    return customerIdList.get(0);
	}
	
	@Transactional
	public Customer getCustomerById(int id) {
	    Customer customer = this.hibernateTemplate.get(Customer.class, id);
	    return customer;
	}


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
