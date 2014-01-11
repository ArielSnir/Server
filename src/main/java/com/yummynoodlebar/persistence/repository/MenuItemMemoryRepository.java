package com.yummynoodlebar.persistence.repository;

import com.yummynoodlebar.persistence.domain.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuItemMemoryRepository implements MenuItemRepository {

	private Map<String, MenuItem> items = new HashMap<String, MenuItem>();

	
	public MenuItemMemoryRepository() {
		
	}

	public MenuItemMemoryRepository(Map<String, MenuItem> items) {
		this.items = items;
	}

/*	@Override
	public MenuItem save(MenuItem item) {
		items.put(item.getId(), item);
		return item;
	}*/

	@Override
	public void delete(String key) {
		items.remove(key);
	}

	@Override
	public MenuItem findById(String key) {
		for (MenuItem item : items.values()) {
			if (item.getId().equals(key)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public List<MenuItem> findAll() {
		return new ArrayList<MenuItem>(items.values());
	}

	@Override
	public <S extends MenuItem> S save(S entity) {
		items.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public <S extends MenuItem> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuItem findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<MenuItem> findAll(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(MenuItem entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends MenuItem> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Integer> analyseIngredientsByPopularity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuItem> findByIngredientsNameIn(String... name) {
		// TODO Auto-generated method stub
		return null;
	}
}
