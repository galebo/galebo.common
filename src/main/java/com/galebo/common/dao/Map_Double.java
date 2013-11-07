package com.galebo.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class Map_Double implements RowMapper<Double>
{
	public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getDouble(1);
	}
}
