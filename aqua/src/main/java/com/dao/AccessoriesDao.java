package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.entities.Accessories;

public class AccessoriesDao {
	private HibernateTemplate hibernateTemplate;

	// insert data
	@Transactional
	public int insert(Accessories accessories) {
		Integer i = (Integer) this.hibernateTemplate.save(accessories);
		return i;
	}

	// deleting the data
	@Transactional
	public void deleteAccessories(int accessoriesId) {
		Accessories accessories = this.hibernateTemplate.get(Accessories.class, accessoriesId);
		this.hibernateTemplate.delete(accessories);
	}

	// updating the data
	@Transactional
	public void updateAccessories(Accessories accessories) {
		this.hibernateTemplate.update(accessories);
	}

	// retrieve all data
	public List<Accessories> getAllAccessorieses() {
		List<Accessories> accessories = this.hibernateTemplate.loadAll(Accessories.class);
		return accessories;
	}

	// get single data(object)
	public Accessories getAccessories(int accessoriesId) {
		Accessories accessories = this.hibernateTemplate.get(Accessories.class, accessoriesId);
		return accessories;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<String> getAccessoriesTypes() {
		@SuppressWarnings("deprecation")
		List<String> accessoriesList = (List<String>) hibernateTemplate.find("SELECT productCategory FROM Accessories");
		return accessoriesList;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public List<Accessories> getAccessoriesesByType(String productCategory) {
		return (List<Accessories>) hibernateTemplate.findByNamedParam(
				"FROM Accessories a WHERE a.productCategory=:productCategory", "productCategory", productCategory);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public String getAccessoriesStockByAccessoriesName(String productName) {
		List<Accessories> accessoriesList = (List<Accessories>) hibernateTemplate
				.findByNamedParam("FROM Accessories WHERE productName = :productName",
		                "productName",
		                productName);
		if (accessoriesList.isEmpty()) {
			return null;
		}
		Accessories accessories= accessoriesList.get(0);
		return accessories.getProductStock();
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
