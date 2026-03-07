package com.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.AllOrderEntity;
import com.wm.entity.OrderEntity;

@Mapper
public interface OrderRepository {
	public int updateOrderInfo(OrderEntity orderInfo);

	public List<OrderEntity> selectOrderList(Long tableId);
	
	public List<AllOrderEntity> selectAllOrder();
	
	public int updateOrderServe(Long orderId, Long dishId);
}
