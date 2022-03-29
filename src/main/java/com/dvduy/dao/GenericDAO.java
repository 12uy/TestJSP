package com.dvduy.dao;

import java.util.List;

import com.dvduy.mapper.RowMapper;

public interface GenericDAO<E> {
	<E> List<E> query(String sql , RowMapper<E> rowMapper, Object... parameters);
	void update(String sql, Object... parameters);
	Long insert(String sql, Object... parameters);
}
