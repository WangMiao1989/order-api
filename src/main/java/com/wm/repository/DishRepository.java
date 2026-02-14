package com.wm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wm.entity.DishEntity;

@Repository
public interface DishRepository {
	public List<DishEntity> findAllDishes();
}
