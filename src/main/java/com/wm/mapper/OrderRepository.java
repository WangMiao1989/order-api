package com.wm.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.OrderInfoEntity;
import com.wm.entity.TableInfoEntity;

@Mapper
public interface OrderRepository {
	public int updateTableInfo(TableInfoEntity tableInfo );
	
	public int updateOrderInfo(OrderInfoEntity orderInfo);
}
