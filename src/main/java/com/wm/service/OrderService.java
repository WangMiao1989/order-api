package com.wm.service;

import java.util.List;

import com.wm.entity.AllOrderEntity;
import com.wm.entity.OrderDetailEntity;
import com.wm.requestDto.OrderServeUpdateRequestForm;
import com.wm.requestDto.OrderUpdateRequestForm;
import com.wm.requestDto.TableNoRequestForm;

public interface OrderService {
	
	public void orderInfoUpdate(OrderUpdateRequestForm orderForm);
	
	public List<OrderDetailEntity> orderListRetrieve(TableNoRequestForm request);
	
	public List<AllOrderEntity> allOrderRetrieve();
	
	public void orderServeUpdate(OrderServeUpdateRequestForm request);
}
