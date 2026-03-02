package com.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.DishEntity;

@Mapper
public interface DishRepository {
	public List<DishEntity> findAllDishes();
}
