package com.wm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.OrderEntity;
import com.wm.entity.TableEntity;
import com.wm.mapper.OrderRepository;
import com.wm.mapper.TableRepository;
import com.wm.requestDto.OrderUpdateRequestForm;
import com.wm.requestDto.TableNoRequestForm;
import com.wm.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	TableRepository tableRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	public void orderInfoUpdate(OrderUpdateRequestForm request) {
		// table信息取得
		TableEntity tableInfo =  tableRepository.selectTableInfo(request.getTableNo());
		// table信息不存在的情况，保存
		if(Objects.isNull(tableInfo)) {
			tableInfo = new TableEntity();
			BeanUtils.copyProperties(request, tableInfo);
			tableRepository.updateTableInfo(tableInfo);
		}
		
		// order info更新
		OrderEntity orderInfo= new OrderEntity();
		orderInfo.setTableId(tableInfo.getTableId());
		orderInfo.setOrderDetail(request.getOrderDetail());
		orderRepository.updateOrderInfo(orderInfo);
	}
	
	public List<OrderEntity> orderListRetrieve(TableNoRequestForm request) {
		// table信息取得
		TableEntity tableInfo =  tableRepository.selectTableInfo(request.getTableNo());
		if(Objects.isNull(tableInfo)) {
			return new ArrayList<>();
		}
		return orderRepository.selectOrderList(tableInfo.getTableId());
	}
}
