package com.dvduy.dao;

import java.util.List;

import com.dvduy.model.NewsModel;

public interface INewDAO extends GenericDAO<NewsModel>{
	NewsModel findById(Long id);
	List<NewsModel> findCategoryByID(Long categoryID);
	Long save(NewsModel newsModel);
	void update(NewsModel newsModel);
	void delete(NewsModel newsModel);
}
