package com.wm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wm.entity.DishEntity;
import com.wm.entity.DishInfoEntity;

@Mapper
public interface DishRepository {
	public List<DishInfoEntity> selectAllDishes();
	
	public int insertDish(DishEntity dish);
	
	public int updateDish(DishEntity dish);
	
	public void deleteDish(Long dishId);
}
