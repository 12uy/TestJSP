package com.dvduy.dao.impl;

import java.util.List;

import com.dvduy.dao.INewDAO;
import com.dvduy.mapper.NewMapper;
import com.dvduy.model.NewsModel;
import com.dvduy.paging.Pageble;
import org.apache.commons.lang3.StringUtils;

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
		String sql = "Insert into news(title, content, categoryid,thumbnail, shortdescription, createddate, modifieddate, createdby, modifiedby ) values(?,?,?,?,?,?,?,?,?)";
		return insert(sql, newsModel.getTitle(), newsModel.getContent(), newsModel.getCategoryId(), newsModel.getThumbnail(), newsModel.getShortDescription(), newsModel.getCreatedDate(), newsModel.getModifiedDate(), newsModel.getCreatedBy(), newsModel.getModifiedBy());
	}

	@Override
	public void update(NewsModel updateNews) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content = ?, categoryid = ?");
		sql.append(" ,createddate =? , modifieddate= ?, createdby =?, modifiedby =? WHERE id = ?");
		update(sql.toString(), updateNews.getTitle(), updateNews.getThumbnail(), updateNews.getShortDescription(), updateNews.getContent(), updateNews.getCategoryId(), updateNews.getCreatedDate(),updateNews.getModifiedDate(), updateNews.getCreatedBy(), updateNews.getModifiedBy() ,  updateNews.getId());
	}


	@Override
	public void delete(Long id) {
		String sql = "Delete from news where id = ?";
		update(sql, id);
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("Select * from news");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortBy())&& StringUtils.isNotBlank(pageble.getSorter().getSortType())) {
			sql.append(" order by " + pageble.getSorter().getSortBy() + " " + pageble.getSorter().getSortType());
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" limit ?,?");
			return query(sql.toString(), new NewMapper(), pageble.getOffset(), pageble.getLimit());
		}
		else
			return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "Select count(*) from news";
		return count(sql);
	}


}
