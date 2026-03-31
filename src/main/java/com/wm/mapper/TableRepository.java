package com.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.TableSessionEntity;
import com.wm.entity.AllTableEntity;

@Mapper
public interface TableRepository {
	public int updateTableInfo(TableSessionEntity tableInfo);
	
	public TableSessionEntity selectTableInfo(String tableNo);
	
	public List<AllTableEntity> selectAllTable();
	
	public int updateTableEndtime(String tableNo);
}
