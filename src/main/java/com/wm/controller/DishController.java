package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.responseDto.DishRetrieveResponse;
import com.wm.service.DishService;

@RestController
@RequestMapping("/dish")
public class DishController {
	
	@Autowired
	private DishService dishService;
	
	@RequestMapping("/retrieve")
	public DishRetrieveResponse dishRetrieve() {
		DishRetrieveResponse response = new DishRetrieveResponse();
		response.setDishList(dishService.dishRetrieve());
		return response;
	}
}
