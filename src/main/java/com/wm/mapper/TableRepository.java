package com.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.TableEntity;
import com.wm.entity.AllTableEntity;

@Mapper
public interface TableRepository {
	public int updateTableInfo(TableEntity tableInfo);
	
	public TableEntity selectTableInfo(String tableNo);
	
	public List<AllTableEntity> selectAllTable();
	
	public int updateTableEndtime(String tableNo);
}
