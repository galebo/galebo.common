package com.galebo.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class Map_Integer implements RowMapper<Integer>
{
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getInt(1);
	}
}
