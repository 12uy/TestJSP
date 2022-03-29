package com.dvduy.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.dvduy.dao.INewDAO;
import com.dvduy.model.NewsModel;
import com.dvduy.service.INewService;

public class NewService implements INewService{

	
	@Inject
	private INewDAO newDAO;
	
	@Override
	public List<NewsModel> findCategoryByID(Long categoryID) {
		// TODO Auto-generated method stub
		return newDAO.findCategoryByID(categoryID);
	}

	@Override
	public NewsModel save(NewsModel newsModel) {
		Long newsID = newDAO.save(newsModel);
		return newDAO.findById(newsID);
	}

	@Override
	public NewsModel update(NewsModel updatedNewsModel) {
		NewsModel oldNewsModel = newDAO.findById(updatedNewsModel.getId()); // get old news
		updatedNewsModel.setCreatedDate(oldNewsModel.getCreatedDate());
		updatedNewsModel.setCreatedBy(oldNewsModel.getCreatedBy());
		newDAO.update(updatedNewsModel);
		return newDAO.findById(updatedNewsModel.getId());

	}

	public void delete(Long[] ids) {
		for (Long id : ids) {
			newDAO.delete(id);
		}
	}

}
