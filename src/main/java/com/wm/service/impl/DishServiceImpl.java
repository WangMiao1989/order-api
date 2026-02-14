package com.wm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.entity.DishEntity;
import com.wm.repository.DishRepository;
import com.wm.service.DishService;

@Service
@Transactional
public class DishServiceImpl implements DishService{
	
	@Autowired
	private DishRepository dishRepository;
	
	public List<DishEntity> dishRetrieve() {
		return dishRepository.findAllDishes();
	}
}
