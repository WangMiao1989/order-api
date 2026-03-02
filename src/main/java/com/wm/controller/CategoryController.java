package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.CategoryEntity;
import com.wm.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/retrieve")
	public List<CategoryEntity> dishRetrieve() {
		return categoryService.categoryRetrieve();
	}
}
