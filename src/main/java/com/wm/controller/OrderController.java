package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.UnservedOrderEntity;
import com.wm.annotation.Idempotent;
import com.wm.entity.OrderDetailEntity;
import com.wm.entity.TableSessionEntity;
import com.wm.requestDto.OrderCheckoutRequestForm;
import com.wm.requestDto.OrderDishCancelRequestForm;
import com.wm.requestDto.OrderDishRequestForm;
import com.wm.requestDto.OrderUpdateRequestForm;
import com.wm.requestDto.TableNoRequestForm;
import com.wm.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Idempotent
	@RequestMapping("/update")
	public TableSessionEntity updateOrder(@RequestBody OrderUpdateRequestForm request) {
		return orderService.orderInfoUpdate(request);
	}
	
	@RequestMapping("/retrieve")
	public List<OrderDetailEntity> orderListRetrieve(@RequestBody TableNoRequestForm request) {
		return orderService.orderListRetrieve(request);
	}
	
	@RequestMapping("/unserved/retrieve")
	public List<UnservedOrderEntity> unservedOrderRetrieve() {
		return orderService.unservedOrderRetrieve();
	}
	
	@RequestMapping("/serve/update")
	public void orderServeUpdate(@RequestBody OrderDishRequestForm request) {
		orderService.orderServeUpdate(request);
	}
	
	@RequestMapping("/dish/cancel")
	public void orderDishQuantityModify(@RequestBody OrderDishCancelRequestForm request) {
		orderService.orderDishCancel(request);
	}
	
	@RequestMapping("/checkout")
	public void orderCheckout(@RequestBody OrderCheckoutRequestForm request) {
		orderService.orderCheckout(request);
	}
}
