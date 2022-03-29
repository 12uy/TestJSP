package com.dvduy.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.dvduy.dao.ICategoryDAO;
import com.dvduy.model.CategoryModel;
import com.dvduy.service.ICategoryService;

public class CategoryService implements ICategoryService{

	
	
	@Inject
	private ICategoryDAO categoryDAO;
	
	
	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		return categoryDAO.findAll();
	}

}
