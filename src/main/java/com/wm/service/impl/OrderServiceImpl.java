package com.wm.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.OrderInfoEntity;
import com.wm.entity.TableInfoEntity;
import com.wm.mapper.OrderRepository;
import com.wm.requestDto.OrderUpdateRequestForm;
import com.wm.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepository;
	
	public void orderInfoUpdate(OrderUpdateRequestForm orderForm) {
		Long tableId = orderForm.getTableId();
		// table info 更新
		if(Objects.isNull(tableId)) {
			TableInfoEntity tableInfo = new TableInfoEntity();
			tableInfo.setTableNo(orderForm.getTableNo());
			tableInfo.setCustomerCnt(orderForm.getCustomerCnt());
			tableInfo.setStartTime(orderForm.getStartTime());
			orderRepository.updateTableInfo(tableInfo);
			tableId = tableInfo.getTableId();
		} 
		
		// order info更新
		OrderInfoEntity orderInfo= new OrderInfoEntity();
		orderInfo.setTableId(tableId);
		orderInfo.setOrderDetail(orderForm.getOrderDetail());
		orderRepository.updateOrderInfo(orderInfo);
	}
}
