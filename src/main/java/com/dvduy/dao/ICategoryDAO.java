package com.dvduy.dao;

import java.util.List;

import com.dvduy.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{
	List<CategoryModel> findAll();
}
