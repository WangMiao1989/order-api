package com.wm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.CategoryEntity;
import com.wm.repository.CategoryRepository;
import com.wm.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryEntity> categoryRetrieve() {
		return categoryRepository.findAllCategorys();
	}
}
