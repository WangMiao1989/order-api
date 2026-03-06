package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.DishInfoEntity;
import com.wm.requestDto.DishDeleteRequestForm;
import com.wm.requestDto.DishUpdateRequestForm;
import com.wm.requestDto.TableDetailRetrieveRequestForm;
import com.wm.service.DishService;

@RestController
@RequestMapping("/dish")
public class DishController {
	
	@Autowired
	private DishService dishService;
	
	@RequestMapping("/retrieve")
	public List<DishInfoEntity> dishRetrieve() {
		return dishService.dishRetrieve();
	}
	
	@RequestMapping("/update")
	public void dishUpdate(@RequestBody DishUpdateRequestForm request) {
		dishService.dishUpdate(request);
	}
	
	@RequestMapping("/delete")
	public void dishDelete(@RequestBody DishDeleteRequestForm request) {
		dishService.dishDelete(request);
	}
}
