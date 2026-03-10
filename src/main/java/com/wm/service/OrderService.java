package com.wm.service;

import java.util.List;

import com.wm.entity.AllOrderEntity;
import com.wm.entity.OrderDetailEntity;
import com.wm.requestDto.OrderCheckoutRequestForm;
import com.wm.requestDto.OrderDishQuantityRequestForm;
import com.wm.requestDto.OrderDishRequestForm;
import com.wm.requestDto.OrderUpdateRequestForm;
import com.wm.requestDto.TableNoRequestForm;

public interface OrderService {
	
	public void orderInfoUpdate(OrderUpdateRequestForm orderForm);
	
	public List<OrderDetailEntity> orderListRetrieve(TableNoRequestForm request);
	
	public List<AllOrderEntity> allOrderRetrieve();
	
	public void orderServeUpdate(OrderDishRequestForm request);
	
	public void orderDishQuantityModify(OrderDishQuantityRequestForm request);
	
	public void orderDishDelete(OrderDishRequestForm request);
	
	public void orderCheckout(OrderCheckoutRequestForm request);
}
