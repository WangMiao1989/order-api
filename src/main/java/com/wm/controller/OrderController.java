package com.wm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.requestDto.OrderUpdateRequestForm;
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
}
