package com.dvduy.dao;

import java.util.List;

import com.dvduy.model.CategoryModel;
import com.dvduy.model.NewsModel;
import com.dvduy.paging.Pageble;

public interface INewDAO extends GenericDAO<NewsModel>{
	NewsModel findById(Long id);
	List<NewsModel> findCategoryByID(Long categoryID);
	Long save(NewsModel newsModel);
	void update(NewsModel newsModel);
	void delete(Long id);
	List<NewsModel> findAll(Pageble pageble);
	int getTotalItem();
}
