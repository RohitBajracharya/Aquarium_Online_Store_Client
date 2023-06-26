package com.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.entities.Fish;

public class FishDao {
	private HibernateTemplate hibernateTemplate;

	// insert data
	@Transactional
	public int insert(Fish fish) {
		Integer i = (Integer) this.hibernateTemplate.save(fish);
		return i;
	}

	// deleting the data
	@Transactional
	public void deleteFish(int fishId) {
		Fish fish = this.hibernateTemplate.get(Fish.class, fishId);
		this.hibernateTemplate.delete(fish);
	}

	// updating the data
	@Transactional
	public void updateFish(Fish fish) {
		this.hibernateTemplate.update(fish);
	}

	// retrieve all data
	public List<Fish> getAllFishes() {
		List<Fish> fishes = this.hibernateTemplate.loadAll(Fish.class);
		return fishes;
	}

	// get single data(object)
	public Fish getFish(int fishId) {
		Fish fish = this.hibernateTemplate.get(Fish.class, fishId);
		return fish;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<String> getFishTypes() {
		@SuppressWarnings("deprecation")
		List<String> fishList = (List<String>) hibernateTemplate.find("SELECT fishType FROM Fish");
		return fishList;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public List<Fish> getFishesByType(String fishType) {
	    return (List<Fish>) hibernateTemplate.findByNamedParam("FROM Fish f WHERE f.fishType=:fishType", "fishType", fishType);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public String getFishStockByFishName(String fishName) {
        List<Fish> fishList = (List<Fish>) hibernateTemplate.findByNamedParam(
                "FROM Fish WHERE fishName = :fishName",
                "fishName",
                fishName
        );

        if (fishList.isEmpty()) {
            return null;
        }

        Fish fish = fishList.get(0);
        return fish.getFishStock();
    }
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
