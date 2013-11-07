package com.galebo.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.galebo.common.bean.IdName;


public class Map_IdName implements RowMapper
{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		IdName bean=new IdName();
		bean.setId(   rs.getInt(1));
		bean.setName(             rs.getString(2));
		return bean;
	}
}
