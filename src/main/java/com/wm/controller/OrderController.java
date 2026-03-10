package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.AllOrderEntity;
import com.wm.entity.OrderDetailEntity;
import com.wm.requestDto.OrderCheckoutRequestForm;
import com.wm.requestDto.OrderDishQuantityRequestForm;
import com.wm.requestDto.OrderDishRequestForm;
import com.wm.requestDto.OrderUpdateRequestForm;
import com.wm.requestDto.TableNoRequestForm;
import com.wm.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/update")
	public void updateOrder(@RequestBody OrderUpdateRequestForm request) {
		orderService.orderInfoUpdate(request);
	}
	
	@RequestMapping("/retrieve")
	public List<OrderDetailEntity> orderListRetrieve(@RequestBody TableNoRequestForm request) {
		return orderService.orderListRetrieve(request);
	}
	
	@RequestMapping("/all-order/retrieve")
	public List<AllOrderEntity> allOrderRetrieve() {
		return orderService.allOrderRetrieve();
	}
	
	@RequestMapping("/serve/update")
	public void orderServeUpdate(@RequestBody OrderDishRequestForm request) {
		orderService.orderServeUpdate(request);
	}
	
	@RequestMapping("/dish-quantity/modify")
	public void orderDishQuantityModify(@RequestBody OrderDishQuantityRequestForm request) {
		orderService.orderDishQuantityModify(request);
	}
	
	@RequestMapping("/dish/delete")
	public void orderDishDelete(@RequestBody OrderDishRequestForm request) {
		orderService.orderDishDelete(request);
	}
	
	@RequestMapping("/checkout")
	public void orderCheckout(@RequestBody OrderCheckoutRequestForm request) {
		orderService.orderCheckout(request);
	}
}
