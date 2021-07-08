package com.zb.utils;

import java.sql.ResultSet;

public interface RowMapper<T> {
	public T makeRow(ResultSet rs);
}
