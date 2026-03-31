package com.wm.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.controller.SseController;
import com.wm.entity.UnservedOrderEntity;
import com.wm.entity.OrderDetailEntity;
import com.wm.entity.OrderEntity;
import com.wm.entity.TableSessionEntity;
import com.wm.exception.BusinessException;
import com.wm.mapper.OrderRepository;
import com.wm.mapper.TableRepository;
import com.wm.requestDto.OrderCheckoutRequestForm;
import com.wm.requestDto.OrderDishCancelRequestForm;
import com.wm.requestDto.OrderDishRequestForm;
import com.wm.requestDto.OrderUpdateRequestForm;
import com.wm.requestDto.TableNoRequestForm;
import com.wm.service.OrderService;
import com.wm.utils.ContextHolder;
import com.wm.utils.TableSessionIdGenerator;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private TableRepository tableRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private SseController sseController;
	
	// order更新
	public void orderInfoUpdate(OrderUpdateRequestForm request) {
		// table信息取得
		TableSessionEntity tableInfo =  tableRepository.selectTableInfo(request.getTableNo());
		// table信息不存在的情况，保存
		if(Objects.isNull(tableInfo)) {
			tableInfo = new TableSessionEntity();
			// 单号生成
			tableInfo.setTableSessionId(TableSessionIdGenerator.generateTableSessionId());
			BeanUtils.copyProperties(request, tableInfo);
			tableRepository.updateTableInfo(tableInfo);
			
			// sse触发table更新
			sseController.sendEmitter(ContextHolder.getTenantId(), "table-updated", "update");
		}
		
		// order info更新
		OrderEntity orderInfo= new OrderEntity();
		orderInfo.setTableSessionId(tableInfo.getTableSessionId());
		orderInfo.setOrderDetail(request.getOrderDetail());
		orderRepository.updateOrderInfo(orderInfo);
		
		// sse触发order更新
		sseController.sendEmitter(ContextHolder.getTenantId(), "order-updated", "{\"type\":\"add\", \"data\":" +request.getOrderDetail() + "}");
	}
	
	// 桌全order取得
	public List<OrderDetailEntity> orderListRetrieve(TableNoRequestForm request) {
		return orderRepository.selectOrderList(request.getTableNo());
	}
	
	// 全order取得	
	public List<UnservedOrderEntity> unservedOrderRetrieve(){
		return orderRepository.selectUnservedOrder();
	}
	
	// 上菜
	public void orderServeUpdate(OrderDishRequestForm request) {
		orderRepository.updateOrderServe(request.getOrderId(), request.getDishId());
	}
	
	// 菜品退订
	public void orderDishCancel(OrderDishCancelRequestForm request) {
		// 更新件数为0件的时候，删除菜品，否者更新菜品数量
		if (request.getQuantity() == 0 ) {
			dishDelete(request.getOrderId(), request.getDishId());
		} else {
			orderRepository.updateOrderDishQuantity(request.getOrderId(), request.getDishId(), request.getQuantity());
		}
		
		// sse触发order更新
		sseController.sendEmitter(ContextHolder.getTenantId(), "order-updated",
				"{\"type\":\"cancel\", \"dishName\":\"" + request.getDishName() +
				"\" ,\"isServed\":" + request.getIsServed() + " ,\"cancelQuantity\":" + request.getCancelQuantity() + "}");
	}
	
	// 结账
	public void orderCheckout(OrderCheckoutRequestForm request) {
		if(Objects.isNull(request.getOrderIdList()) || request.getOrderIdList().size() == 0) {
			throw new BusinessException("no pay target","支付对象不存在！");
		}
		orderRepository.updateOrderPay(request.getOrderIdList());
	}
	
	private void dishDelete(Long orderId, Long dishId) {
		// order中当前菜品以外的菜品件数取得
		Integer cnt =  orderRepository.selectOtherDishCount(orderId, dishId);
		if(cnt == 0) {
			// 当前order只有当前菜品的场合，全order删除
			orderRepository.deleteOrder(orderId);
		}else {
			// 当菜品以外还存在的场合，只删除当前菜品
			orderRepository.deleteOrderDish(orderId, dishId);
		}
	}
}
