package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.DishEntity;
import com.wm.service.DishService;

@RestController
@RequestMapping("/dish")
public class DishController {
	
	@Autowired
	private DishService dishService;
	
	@RequestMapping("/retrieve")
	public List<DishEntity> dishRetrieve() {
		return dishService.dishRetrieve();
	}
}
