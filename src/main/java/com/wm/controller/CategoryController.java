package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.responseDto.CategoryRetrieveResponse;
import com.wm.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/retrieve")
	public CategoryRetrieveResponse dishRetrieve() {
		CategoryRetrieveResponse response = new CategoryRetrieveResponse();
		response.setCategoryList(categoryService.categoryRetrieve());
		return response;
	}
}
