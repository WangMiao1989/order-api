package com.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.UnservedOrderEntity;
import com.wm.entity.OrderDetailEntity;
import com.wm.entity.OrderEntity;

@Mapper
public interface OrderRepository {
	public int updateOrderInfo(OrderEntity orderInfo);

	public List<OrderDetailEntity> selectOrderList(String tableNo);
	
	public List<UnservedOrderEntity> selectUnservedOrder();
	
	public int updateOrderServe(Long orderId, Long dishId);
	
	public int selectOtherDishCount(Long orderId, Long dishId);
	
	public int deleteOrder(Long orderId);
	
	public int deleteOrderDish(Long orderId, Long dishId);
	
	public int updateOrderDishQuantity(Long orderId, Long dishId, Integer quantity);
	
	public int updateOrderPay(List<Long> orderIdList);
}
