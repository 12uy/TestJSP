package com.dvduy.service;

import com.dvduy.model.NewsModel;
import com.dvduy.paging.Pageble;

import java.util.List;

public interface INewsService {
	public List<NewsModel> findCategoryByID(Long categoryID);
	public NewsModel save(NewsModel newsModel);
	public NewsModel update(NewsModel updateNewsModel);
	public void delete(Long[] ids);
	List<NewsModel> findAll(Pageble pageble);
	int getTotalItem();
}
