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

	@Override
	public CategoryModel findByCategiryId(long id) {
		String sql = "Select * from category where id = ?";
		List<CategoryModel> list = query(sql, new CategoryMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public CategoryModel findByCategoryCode(String code) {
		String sql = "Select * from category where code = ?";
		List<CategoryModel> list = query(sql, new CategoryMapper(), code);
		return list.isEmpty() ? null : list.get(0);
	}
}
