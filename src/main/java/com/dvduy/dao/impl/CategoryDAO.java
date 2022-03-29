package com.dvduy.dao.impl;

import java.util.List;

import com.dvduy.dao.ICategoryDAO;
import com.dvduy.mapper.CategoryMapper;
import com.dvduy.model.CategoryModel;


public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO{

	
	
	
	@Override
	public List<CategoryModel> findAll() {
		
		String sql = "Select * from category";
		return query(sql,  new CategoryMapper());
		
	}
}
