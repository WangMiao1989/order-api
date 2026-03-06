package com.wm.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.TableEntity;

@Mapper
public interface TableRepository {
	public int updateTableInfo(TableEntity tableInfo);
	
	public TableEntity selectTableInfo(String tableNo);
}
