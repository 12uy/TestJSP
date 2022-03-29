package com.dvduy.service;

import com.dvduy.model.NewsModel;

import java.util.List;

public interface INewService {
	public List<NewsModel> findCategoryByID(Long categoryID);
	public NewsModel save(NewsModel newsModel);
	public NewsModel update(NewsModel updateNewsModel);
	public void delete(Long[] ids);
}
