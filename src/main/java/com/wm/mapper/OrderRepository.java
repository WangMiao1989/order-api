package com.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.OrderEntity;
import com.wm.entity.TableEntity;

@Mapper
public interface OrderRepository {
	public int updateOrderInfo(OrderEntity orderInfo);

	public List<OrderEntity> selectOrderList(Long tableId);
}
