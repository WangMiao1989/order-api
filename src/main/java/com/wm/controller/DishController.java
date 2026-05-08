package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.DishInfoEntity;
import com.wm.exception.BusinessException;
import com.wm.requestDto.DishDeleteRequestForm;
import com.wm.requestDto.DishDisplayUpdateRequestForm;
import com.wm.requestDto.DishUpdateRequestForm;
import com.wm.service.DishService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	public void dishUpdate(@Valid @RequestBody DishUpdateRequestForm request, Errors error) {
		if(error.hasErrors()) {
			throw new BusinessException(error);
		}
		dishService.dishUpdate(request);
	}
	
	@RequestMapping("/delete")
	public void dishDelete(@RequestBody DishDeleteRequestForm request) {
		dishService.dishDelete(request);
	}
	
	@RequestMapping("/display/update")
	public void dishDisplayUpdate(@RequestBody DishDisplayUpdateRequestForm request) {
		dishService.dishDisplayUpdate(request);
	}
}
