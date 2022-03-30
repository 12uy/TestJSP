package com.dvduy.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dvduy.model.NewsModel;

public class NewMapper implements RowMapper<NewsModel>{

	@Override
	public NewsModel mapRow(ResultSet resultSet) {
		try {
			NewsModel news = new NewsModel();
			news.setId(resultSet.getLong("id"));
			news.setTitle(resultSet.getString("title"));
			news.setContent(resultSet.getString("content"));
			news.setCategoryId(resultSet.getLong("categoryid"));
			news.setThumbnail(resultSet.getString("thumbnail"));
			news.setCreatedDate(resultSet.getTimestamp("createddate"));
			news.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			news.setShortDescription(resultSet.getString("shortdescription"));
			news.setModifiedBy(resultSet.getString("modifiedby"));
			news.setCreatedBy(resultSet.getString("createdby"));


			return news;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
