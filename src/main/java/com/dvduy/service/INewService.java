package com.dvduy.service;

import java.util.List;

import com.dvduy.model.NewsModel;

public interface INewService {
	public List<NewsModel> findCategoryByID(Long categoryID);
	public NewsModel save(NewsModel newsModel);
	public void update(NewsModel newsModel);
	public void delete(NewsModel newsModel);
}
