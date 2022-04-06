package com.dvduy.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.enterprise.inject.New;
import javax.inject.Inject;

import com.dvduy.dao.ICategoryDAO;
import com.dvduy.dao.INewDAO;
import com.dvduy.model.CategoryModel;
import com.dvduy.model.NewsModel;
import com.dvduy.paging.Pageble;
import com.dvduy.service.INewsService;

public class NewsService implements INewsService {

	
	@Inject
	private INewDAO newDAO;

	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<NewsModel> findCategoryByID(Long categoryID) {
		// TODO Auto-generated method stub
		return newDAO.findCategoryByID(categoryID);
	}

	@Override
	public NewsModel save(NewsModel newsModel) {
		newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel categoryModel = categoryDAO.findByCategoryCode(newsModel.getCategoryCode());
		newsModel.setCategoryId(categoryModel.getId());
		Long newsID = newDAO.save(newsModel);
		return newDAO.findById(newsID);
	}

	@Override
	public NewsModel update(NewsModel updatedNewsModel) {
		NewsModel oldNewsModel = newDAO.findById(updatedNewsModel.getId()); // get old news
		updatedNewsModel.setCreatedDate(oldNewsModel.getCreatedDate());
		updatedNewsModel.setCreatedBy(oldNewsModel.getCreatedBy());
		updatedNewsModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel categoryModel = categoryDAO.findByCategoryCode(updatedNewsModel.getCategoryCode());
		updatedNewsModel.setCategoryId(categoryModel.getId());
		newDAO.update(updatedNewsModel);
		return newDAO.findById(updatedNewsModel.getId());

	}

	public void delete(Long[] ids) {
		for (Long id : ids) {
			newDAO.delete(id);
		}
	}



	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		return newDAO.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newDAO.getTotalItem();
	}

	@Override
	public NewsModel findById(Long id) {
		NewsModel newsModel = newDAO.findById(id);
		CategoryModel categoryModel = categoryDAO.findByCategiryId(newsModel.getCategoryId());
		newsModel.setCategoryCode(categoryModel.getCode());
		return newsModel;
	}

}
