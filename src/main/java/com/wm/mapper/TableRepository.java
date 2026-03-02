package com.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.TableInfoEntity;

@Mapper
public interface TableRepository {
	public TableInfoEntity selectTableInfo(String tableNo);
	
	public List<Object> selectOrderList(Long tableId);
}
