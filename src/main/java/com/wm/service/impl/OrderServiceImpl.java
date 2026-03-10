package com.wm.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.controller.SseController;
import com.wm.entity.AllOrderEntity;
import com.wm.entity.OrderDetailEntity;
import com.wm.entity.OrderEntity;
import com.wm.entity.TableEntity;
import com.wm.exception.BusinessException;
import com.wm.mapper.OrderRepository;
import com.wm.mapper.TableRepository;
import com.wm.requestDto.OrderCheckoutRequestForm;
import com.wm.requestDto.OrderDishQuantityRequestForm;
import com.wm.requestDto.OrderDishRequestForm;
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
	
	@Autowired
	SseController sseController;
	
	// order更新
	public void orderInfoUpdate(OrderUpdateRequestForm request) {
		// table信息取得
		TableEntity tableInfo =  tableRepository.selectTableInfo(request.getTableNo());
		// table信息不存在的情况，保存
		if(Objects.isNull(tableInfo)) {
			tableInfo = new TableEntity();
			BeanUtils.copyProperties(request, tableInfo);
			tableRepository.updateTableInfo(tableInfo);
			
			// sse触发table更新
			sseController.notifyTableUpdated();
		}
		
		// order info更新
		OrderEntity orderInfo= new OrderEntity();
		orderInfo.setTableId(tableInfo.getTableId());
		orderInfo.setOrderDetail(request.getOrderDetail());
		orderRepository.updateOrderInfo(orderInfo);
		
		// sse触发order更新
		sseController.notifyOrderUpdated(request.getOrderDetail());
	}
	
	// 桌全order取得
	public List<OrderDetailEntity> orderListRetrieve(TableNoRequestForm request) {
		return orderRepository.selectOrderList(request.getTableNo());
	}
	
	// 全order取得	
	public List<AllOrderEntity> allOrderRetrieve(){
		return orderRepository.selectAllOrder();
	}
	
	// 上菜
	public void orderServeUpdate(OrderDishRequestForm request) {
		orderRepository.updateOrderServe(request.getOrderId(), request.getDishId());
	}
	
	// 菜品更新
	public void orderDishQuantityModify(OrderDishQuantityRequestForm request) {
		orderRepository.updateOrderDishQuantity(request.getOrderId(), request.getDishId(), request.getQuantity());
		
		// sse触发order更新
		sseController.notifyOrderUpdated("[{'dishId':" + request.getDishId() + "}]");
	}
	
	// 菜品删除
	public void orderDishDelete(OrderDishRequestForm request) {
		// order中当前菜品以外的菜品件数取得
		Integer cnt =  orderRepository.selectOtherDishCount(request.getOrderId(), request.getDishId());
		if(cnt == 0) {
			// 当前order只有当前菜品的场合，全order删除
			orderRepository.deleteOrder(request.getOrderId());
		}else {
			// 当菜品以外还存在的场合，只删除当前菜品
			orderRepository.deleteOrderDish(request.getOrderId(), request.getDishId());
		}
		
		// sse触发order更新
		sseController.notifyOrderUpdated("[{'dishId':" + request.getDishId() + "}]");
	}
	
	// 结账
	public void orderCheckout(OrderCheckoutRequestForm request) {
		if(Objects.isNull(request.getOrderIdList()) || request.getOrderIdList().size() == 0) {
			throw new BusinessException("no pay target","支付对象不存在！");
		}
		orderRepository.updateOrderPay(request.getOrderIdList());
	}
}
