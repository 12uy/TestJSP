package com.dvduy.dao.impl;

import java.util.List;

import com.dvduy.dao.INewDAO;
import com.dvduy.mapper.NewMapper;
import com.dvduy.model.NewsModel;

public class NewDAO extends AbstractDAO<NewsModel> implements INewDAO{

	//find one news by id
	@Override
	public NewsModel findById(Long id) {
		String sql = "Select * from news where id = ?";
		List<NewsModel> list = query(sql, new NewMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	//find n
	@Override
	public List<NewsModel> findCategoryByID(Long categoryID) {
	
		String sql = "Select * from news where categoryid = ?";
		return query(sql, new NewMapper(), categoryID);
		
	}

	@Override
	public Long save(NewsModel newsModel) {
		String sql = "Insert into news(title, content, categoryid) values(?,?,?)";
		return insert(sql, newsModel.getTitle(), newsModel.getContent(), newsModel.getCategoryId());
	}

	@Override
	public void update(NewsModel newsModel) {
		String sql = "Update news set title = ?, content = ?, categoryid = ? where id = ?";
		update(sql, newsModel.getTitle(), newsModel.getContent(), newsModel.getCategoryId(), newsModel.getId());
	}

	@Override
	public void delete(NewsModel newsModel) {
		String sql = "Delete from news where id = ?";
		update(sql, newsModel.getId());
	}


}
